import pandas as pd
import numpy as np
import numpy
from numpy import array
from sklearn.preprocessing import MinMaxScaler
from sqlalchemy import create_engine
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, LSTM
from sqlalchemy.orm import close_all_sessions

def create_dataset(dataset, time_step=1):
    dataX, dataY = [], []
    for i in range(len(dataset)-time_step-1):
        a = dataset[i:(i+time_step), 0]   ###i=0, 0,1,2,3-----99   100 
        dataX.append(a)
        dataY.append(dataset[i + time_step, 0])
    return numpy.array(dataX), numpy.array(dataY)

def price_prediction(request):
    req = request.get_json()
    hari = req['hari']
    engine = create_engine("mysql+pymysql://root:angkat_tani@34.101.152.190")
    sql = engine.connect()

    sql.execute("CREATE DATABASE IF NOT EXISTS dataset")
    sql.execute("USE dataset")

    data = pd.read_sql("SELECT * FROM harga_pangan", con=sql)
    data['tanggal'] = pd.to_datetime(data['tanggal'], infer_datetime_format=True)

    df1=data.reset_index()['bawang_merah']
    scaler=MinMaxScaler(feature_range=(0,1))
    df1=scaler.fit_transform(np.array(df1).reshape(-1,1))

    training_size=int(len(df1)*0.75)
    test_size=len(df1)-training_size
    train_data,test_data=df1[0:training_size,:],df1[training_size:len(df1),:1]

    time_step = 100
    X_train, y_train = create_dataset(train_data, time_step)
    X_test, ytest = create_dataset(test_data, time_step)

    X_train =X_train.reshape(X_train.shape[0],X_train.shape[1] , 1)
    X_test = X_test.reshape(X_test.shape[0],X_test.shape[1] , 1)

    model=Sequential()
    model.add(LSTM(50,return_sequences=True,input_shape=(100,1)))
    model.add(LSTM(50,return_sequences=True))
    model.add(LSTM(50))
    model.add(Dense(1))

    early_stopping = tf.keras.callbacks.EarlyStopping(monitor= 'val_loss',
                                                    patience=3,
                                                    mode='min')

    model.compile(loss=tf.losses.MeanSquaredError(), 
                optimizer='adam', 
                metrics = [tf.metrics.MeanAbsoluteError()])

    history = model.fit(X_train,y_train, epochs = 50,
                        validation_data = (X_test,ytest),                    
                        shuffle = False,
                        batch_size = 64,
                        verbose = 1,
                        callbacks=[early_stopping])

    train_predict=model.predict(X_train)
    test_predict=model.predict(X_test)

    ##Transformback to original form
    train_predict=scaler.inverse_transform(train_predict)
    test_predict=scaler.inverse_transform(test_predict)

    x_input=test_data[len(test_data)-100:].reshape(1,-1)

    temp_input=list(x_input)
    temp_input=temp_input[0].tolist()

    # demonstrate prediction for next 30 days
    lst_output=[]
    n_steps=100
    i=0
    while(i<hari):
        
        if(len(temp_input)>100):
            #print(temp_input)
            x_input=np.array(temp_input[1:])
            #rint("{} day input {}".format(i,x_input))
            x_input=x_input.reshape(1,-1)
            x_input = x_input.reshape((1, n_steps, 1))
            #print(x_input)
            yhat = model.predict(x_input, verbose=0)
            #print("{} day output {}".format(i,yhat))
            temp_input.extend(yhat[0].tolist())
            temp_input=temp_input[1:]
            #print(temp_input)
            lst_output.extend(yhat.tolist())
            i=i+1
        else:
            x_input = x_input.reshape((1, n_steps,1))
            yhat = model.predict(x_input, verbose=0)
            print(yhat[0])
            temp_input.extend(yhat[0].tolist())
            print(len(temp_input))
            lst_output.extend(yhat.tolist())
            i=i+1
        
    day_new=np.arange(1,101)
    day_pred=np.arange(101,131)

    df3=df1.tolist()
    df3.extend(lst_output)
    df3=scaler.inverse_transform(df3).tolist()

    df3 = pd.DataFrame(df3, columns = ['Prediction']).round(2)
    df3.insert(0,'Date',pd.date_range(start = '2017-07-31', periods = df3.shape[0],freq ='D').astype(str))

    sql.execute("DROP TABLE IF EXISTS prediksi")
    df3.to_sql('prediksi', con=sql, index = False, if_exists='replace')
    sql.close()
    engine.dispose()
    return {"Predictions" : "Success predict {} days and saved to MySQL".format(hari)}

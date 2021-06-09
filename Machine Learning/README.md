
# MACHINE LEARNING

  In this project there are 2 part of machine learning :
  1. Forecasting Crop Sales
  2. Plant Disease Detection


  ## Forecasting Crop Sales


   Dataset :  using dataset from PIHPS Nasional web (https://hargapangan.id/tabel-harga/pasar-tradisional/daerah) 

   The dataset  have a date range from August 2017 - April 2021

   ![image](https://user-images.githubusercontent.com/51345434/121324873-561e1e80-c93b-11eb-91c4-54d0470014ce.png)
      .....
      ![image](https://user-images.githubusercontent.com/51345434/121325237-a1d0c800-c93b-11eb-80a8-9447310efd36.png)

We use 5 commodities for penetrating market according five most popular commodity in Indonesia.
1. Shallot
2. Rice
3. Garlic 
4. Red Chili Pepper
5. Cayenne Pepper

### Handling Missing Value
The dataset from PHIPS Nastional web have lots of missing values that will have an affect  to forecasting models. Forecasting or a time series is a set of chronologically ordered points so we have to handle the missing values first.

![image](https://user-images.githubusercontent.com/51345434/121326497-d2fdc800-c93c-11eb-91b5-50cee40d39af.png)

#### Handling Missing Value wwith Interpolate

![image](https://user-images.githubusercontent.com/51345434/121326573-e27d1100-c93c-11eb-9d97-28af72ac10fd.png)

### Models
We try to use FBProphet & LSTM Models
### 1. FBProphet Model 
FBProphet perform well at the beginning but worst to predict new data because the data doesnt have trends and seasonality.

![image](https://user-images.githubusercontent.com/51345434/121327757-ef4e3480-c93d-11eb-9172-2b94224b8d4d.png)

### 2. LSTM Models
We make a simple LSTM models with 100 time step to predict 1 new data and loop the model 30 times, so we can get 30 days new data.
The dataset have a date range from august 2017 until may 2021. we just use data until Aprill 2021 to predict 30 days data on may 2021. The prediction have a good result with mean absolute error of 182.66 on the inverse scale test data. 

![image](https://user-images.githubusercontent.com/51345434/121328937-f295f000-c93e-11eb-8975-50eb03955736.png)

![image](https://user-images.githubusercontent.com/51345434/121329115-1ce7ad80-c93f-11eb-8351-cafedcbcef2f.png)


## Plant Disease Detection





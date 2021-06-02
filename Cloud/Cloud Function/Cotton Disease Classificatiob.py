import numpy as np
import os
from tensorflow.keras.models import load_model
import json
from google.cloud import storage
from tensorflow.keras.preprocessing.image import load_img, img_to_array
from tensorflow.keras.applications.inception_v3 import preprocess_input
import PIL

## Gloabl model variable
disease = None

def disease_prediction(request):

    # Use the global model variable 
    global disease

    params = request.get_json()
    name = params['images']

    BUCKET_NAME        = "angkat_tani"
    PROJECT_ID         = "angkat-tani-40404"
    IMG                = 'images/'+name
    model              = 'disease.h5'

    # Initialise a client
    client   = storage.Client(PROJECT_ID)

    # Create a bucket object for our bucket
    bucket   = client.get_bucket(BUCKET_NAME)

    # Create a blob object from the filepath
    blob     = bucket.blob(IMG)

    folder = '/tmp/'
    if not os.path.exists(folder):
        os.makedirs(folder)
    # Download the file to a destination

    blob.download_to_filename(folder + name)

    if not disease:
        
        blob = bucket.blob(model)
        blob.download_to_filename(folder + model)
        disease = load_model("/tmp/disease.h5")

    img = img_to_array(load_img(folder + name,target_size=(224,224)))/255
    img = np.expand_dims(img,axis=0)
    img_data = preprocess_input(img)

    if (params is not None) and ('images' in params):
        # Run a test prediction
        preds  = disease.predict(img_data)
        preds = np.argmax(preds, axis=1)
        if preds == 0:
            preds = "The leaf is diseased cotton leaf"
        elif preds == 1:
            preds = "The leaf is diseased cotton plant"
        elif preds == 2:
            preds = "The leaf is fresh cotton leaf"
        else:
            preds = "The leaf is fresh cotton plant"
        prediction = {"Predictions" : preds}
        return prediction
    else:
        prediction ={"Predictions" : "Wrong Input Format"}
        return prediction
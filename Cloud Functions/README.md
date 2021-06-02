## For classification of plant diseases:
1. Generate PHP program code to upload image files from the website to cloud bucket
2. Upload the machine learning model to the cloud bucket, in this case, the h5 format is used
3. The uploaded files, namely machine learning models and image files, will be downloaded to the cloud functions tmp folder using coding from the cloud function itself. Then the h5 machine learning model is loaded and the classification of the uploaded image is carried out.
4. After the cloud function is finished, use the HTTP trigger to make the cloud functions run. In this case, a post request from the website is used. Post requests must be in JSON format in order to be processed in cloud functions.

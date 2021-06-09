
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

For this project, we create some multiclass classification of plant disease like rice leaf, tomato leaf, and cotton leaf.

### Dataset
Training Data and Testing Data that were used are sourced from kaggle
1. Rice leaf : https://www.kaggle.com/minhhuy2810/rice-diseases-image-dataset
2. Tomato leaf https://www.kaggle.com/kaustubhb999/tomatoleaf 
3. cotton leaf https://www.kaggle.com/janmejaybhoi/cotton-disease-dataset

### 1. Rice Leaf

We use inceptionV3 and mobilenetV2 for the architecture models.
From the mobilenetV2 models we get 51% and the InceptionV3 models we get 54% validation accuracy

### InceptionV3
![image](https://user-images.githubusercontent.com/51345434/121335303-a9e13580-c944-11eb-8ebe-18c3b970a523.png)


### MobilenetV2
![image](https://user-images.githubusercontent.com/51345434/121335132-85855900-c944-11eb-8aa3-1da27c2c1422.png)

### 2. Tomato Leaf
We also use inceptionV3 and mobilenetV2 for the architecture models. From the mobilenetV2 models we get 97% validation accuracy and the InceptionV3 models we get 93% validation accuracy

### Inception V3
![image](https://user-images.githubusercontent.com/51345434/121335759-15c39e00-c945-11eb-86c5-2edc26808ecf.png)

### MobilenetV2
![image](https://user-images.githubusercontent.com/51345434/121335909-3a1f7a80-c945-11eb-9425-f9cf9c10ac79.png)

### 3. Cotton Leaf
we use inceptionV3 and resnet152V2 for the architecture models. From the inceptionV3 models we get 95% validation accuracy and from the resnet152V2 models we also get 95% validation accuracy, but the inceptionV3 models perform worst when predicting new data compared to the resnet152V2 models.

### InceptionV3
![image](https://user-images.githubusercontent.com/51345434/121336236-87035100-c945-11eb-9f91-2bbcb2e918b0.png)

### Resnet152V2
![image](https://user-images.githubusercontent.com/51345434/121336393-aac69700-c945-11eb-90f3-50e2c317d373.png)
![image](https://user-images.githubusercontent.com/51345434/121336437-b44fff00-c945-11eb-9fb2-0937d6d38bb4.png)





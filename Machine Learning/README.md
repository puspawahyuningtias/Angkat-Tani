
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

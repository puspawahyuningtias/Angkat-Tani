<h1 align="center"> Cloud Computing </h1>

<br>
<h3>In this project, cloud computing need to do:</h3>
<ul>
  <li>Deploy machine learning model in cloud</li>
  <li>Make and deploy website</li>
  <li>Create and organize database</li>
  <li>Deploy android application in cloud</li>
</ul>
<br>

<h3>To make Angkat Tani's website and android application, we use several service from Google Cloud Platform (GCP) which can be seen in the image below.</h3>
<br>
<div align="center">
  <img src="https://github.com/puspawahyuningtias/Angkat-Tani/blob/master/Cloud%20Computing/Planning/service.png" alt="GCP Service" width="70%" height="50%">
</div>
<br>
<p>
  We choose cloud SQL as database because it is easy to integrate with websites, android, and machine learning.
  Cloud storage to save our data because it suitable to use and cloud storage provides fast, low-cost, highly durable storage.
  Cloud Functions to deploy machine learning models, because it is simple, serverless, and supports the Python programming language.
  Also, the integration of cloud functions with websites and android is easy to do.
  And last, We choose App Engine to deploy our website, becuause App Engine which is Platform as a Service,
  so we don't have to pay attention to cloud infrastructure. And, App Engine can also work with other GCP services like Cloud SQL.
</p>
<br>

<h3>We also make block diagram to make our work easier. There was three diagram block. that is: </h3>
<ul>
  <li>Admin to do price prediction</li>
  <li>User to access price prediction result</li>
  <li>User to detect plant disease</li>
</ul>
<br>

<h4>Admin to do price prediction</h4>
<br>
<div align="center">
  <img src="https://github.com/puspawahyuningtias/Angkat-Tani/blob/master/Cloud%20Computing/Planning/dg_admin.png" alt="Admin_Price_Prediction" width="70%" height="50%">
</div>
<br>
<p>
  Admin will log in to the website and access machine learning model in cloud function using post request method,
   the result of this process is price prediction that will save in Cloud SQL.
 </p>
 <br>

<h4>User to access price prediction result</h4>
<br>
<div align="center">
  <img src="https://github.com/puspawahyuningtias/Angkat-Tani/blob/master/Cloud%20Computing/Planning/dg_price_prediction.png" alt="User_Price_Prediction" width="70%" height="50%">
</div>
<br>
<p>
  User need to use android application or website and send request to Cloud SQL,
   then Cloud SQL will give the result that will be showed by android application or website.
 </p>
 <br>

<h4>User to detect plant disease</h4>
<br>
<div align="center">
  <img src="https://github.com/puspawahyuningtias/Angkat-Tani/blob/master/Cloud%20Computing/Planning/dg_plant_disease.png" alt="User_Plant_Disease" width="70%" height="50%">
</div>
<br>
<p>
  User need to use android application or website to access machine learning model in cloud function,
   this process is done by using post request method. After processing input,
  Cloud Function will give the plant disease result that will be showed by android application or website.
 </p>
 <br>
 
<h3> After planning those thing, we also create mock up then change it into a reachable aplication. You can access using this link</h3>
<ul>
  <li><a href="https://storage.googleapis.com/aplikasi_angkat_tani/Angkat_Tani%20V1.apk">Android application</a></li>
  <li><a href="https://angkat-tani-40404.et.r.appspot.com/">Website</a></li>
</ul>


  
  

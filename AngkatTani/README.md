
<h1 align="center"> Android </h1>

<br>
<h3>In this project, Android need to do:</h3>
<ul>
  <li>Minimun Android Studio Version 4.0</li>
  <li>Deploy machine learning model in cloud and in android with tensorflow lite</li>
  <li>Create REST API to connect Cloud to get data</li>
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

<h3>There is the flowchart for this application.</h3>
<br>
<div align="center">
  <img src="https://github.com/puspawahyuningtias/Angkat-Tani/blob/master/AngkatTani/flow.png" alt="GCP Service" width="70%" height="50%">
</div>
<br>
<h3>Testing apps</h3>
<br>
  <ul>
  <li>Import database : https://github.com/puspawahyuningtias/Angkat-Tani/blob/master/AngkatTani/dbangkattani2.sql </li>
  <li>Import REST API and ajust connection : https://github.com/puspawahyuningtias/Angkat-Tani/blob/master/AngkatTani/api_capstone.zip </li>
  </ul>

  
  

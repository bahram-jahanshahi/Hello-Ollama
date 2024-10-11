## Assembly AI SSL Certificate
If it's needed to add the api.assemblyai.com cert to java security cacert  
### Download the crt or pem
Open the browser and locate https://api.assemblyai.com/v2/transcript and download the crt or pem file  
### Remove the current assemblyai alias from
```shell 
keytool -delete -alias assemblyai -keystore C:\ws\SEB\Softwares\jdk-17.0.2\lib\security\cacerts
```
### Add the certificate
```shell 
 keytool -import -alias assemblyai -file .\api.assemblyai.com.crt -keystore C:\ws\SEB\Softwares\jdk-17.0.2\lib\security\cacerts
```

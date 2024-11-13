cd ..
docker build -t ev-data-mgmt:1.0 .
minikube image load ev-data-mgmt:1.0
cd bin
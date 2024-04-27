# make sure mysql is running, look at application-*.properties for reference

./mvnw clean
./mvnw package

docker build -t inventory .
docker tag inventory:latest johanes3/inventory:3.0.1
docker push johanes3/inventory:3.0.1

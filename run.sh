#!/bin/bash

gradle build

if [[ "$1" == "docker" ]]; then
    docker rm orders_api --force | true
    docker rmi orders_api | true

    docker build -t orders_api .
    docker run -d --name orders_api -p 8080:8080 orders_api
    docker logs -f orders_api
else
    gradle bootRun
fi
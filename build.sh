#!/bin/bash

set -e

REMOTE_IP=
DOCKER_IMAGE_NAME=akka-http-k8s
LOCAL_REGISTRY=127.0.0.1:5000
PROD_VERSION=v1.0.0

case $1 in
    "build")
        sbt clean assembly
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
    ;;
    "local")
        sbt clean assembly
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
        docker tag akka-http-k8s:latest 127.0.0.1:5000/akka-http-k8s:latest
        docker push 127.0.0.1:5000/akka-http-k8s:latest
    ;;
esac
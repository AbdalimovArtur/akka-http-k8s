#!/bin/bash

set -e

REMOTE_IP=
DOCKER_IMAGE_NAME=akka-http-k8s
LOCAL_REGISTRY=127.0.0.1:5000
PROD_VERSION=v1.0.0

case $1 in
    "build")
        sbt clean compile dist
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
    ;;
    "local")
        sbt clean compile dist
        docker build --no-cache -t $DOCKER_IMAGE_NAME .
        docker tag $DOCKER_IMAGE_NAME:latest $LOCAL_REGISTRY/$DOCKER_IMAGE_NAME:latest
        docker push $LOCAL_REGISTRY/$DOCKER_IMAGE_NAME:latest
    ;;
esac
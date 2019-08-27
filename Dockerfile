FROM openjdk:8-jre-alpine

MAINTAINER Artur Abdalimov <a.abdalimov.97@gmail.com>

RUN apk add --no-cache tzdata

ENV TZ=Asia/Almaty

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./target/scala-2.12/akka-http-k8s-assembly-1.0.0.jar usr/app/
WORKDIR /usr/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dconfig.resource=local.conf", "./akka-http-k8s-assembly-1.0.0.jar"]

FROM openjdk:8u212-b04-jdk-stretch

MAINTAINER Artur Abdalimov <a.abdalimov.97@gmail.com>

ENV LANG 'en_EN.UTF-8'
ENV APP_NAME 'akka-http-k8s-1.0.0.zip'
ENV APP_DIR 'akka-http-k8s-1.0.0'
ENV RUN_SCRIPT 'akka-http-k8s'
ENV JAVA_OPTS '-server -Xms128M -Xmx512M'

WORKDIR /root
COPY ./target/universal/$APP_NAME /root/
RUN unzip -q -o $APP_NAME
WORKDIR /root/$APP_DIR/bin

RUN rm /root/$APP_NAME
CMD chmod +x $RUN_SCRIPT
EXPOSE 8080

CMD ["/bin/bash", "-c", "./$RUN_SCRIPT -Dconfig.resource=local.conf"]

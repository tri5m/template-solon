#FROM eclipse-temurin:17.0.11_9-jre-jammy
FROM ibm-semeru-runtimes:open-17.0.10_7-jre-jammy

MAINTAINER trifolium <trifolium.wang@gmail.com>

LABEL app="template-solon-service" version="1.0-SNAPSHOT" by="trifolium"

ENV APP_VERSION 1.0-SNAPSHOT

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone \
    && mkdir /app \
    && mkdir /app/logs

WORKDIR /app

COPY services/target/template-solon-service.jar /app/app.jar

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:InitialRAMPercentage=70.0", \
"-XX:MaxRAMPercentage=70.0", "-jar", "app.jar"]

EXPOSE 9090
FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080
# cd /opt/app
WORKDIR /opt/app

COPY target/api-*.jar finance-webapp-api.jar

ENTRYPOINT ["java","-jar","/opt/app/finance-webapp-api.jar"]
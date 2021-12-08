FROM openjdk:11.0.9.1-jdk as BUILD
COPY . /build
CMD chmod -R 777 /build
WORKDIR /build
RUN ./mvnw clean install

FROM openjdk:11.0.9.1-jre
EXPOSE 8080
RUN mkdir /app
    RUN curl -O "https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip" && \
        unzip newrelic-java.zip && \
        rm newrelic-java.zip
COPY --from=build /build/target/*new-relic-kafka-demo*.jar /app/demo.jar
RUN cp ./newrelic/newrelic.jar /app/newrelic.jar
#COPY ./newrelic/newrelic.jar /app/newrelic.jar
ENTRYPOINT ["java","-javaagent:/app/newrelic.jar","-jar","/app/demo.jar"]
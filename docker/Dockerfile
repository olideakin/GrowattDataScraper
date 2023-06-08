FROM eclipse-temurin:20.0.1_9-jre-alpine

WORKDIR /usr/local/growatt
COPY target/GrowattDataScraper-jar-with-dependencies.jar GrowattDataScraper.jar

ENTRYPOINT ["java","-jar","GrowattDataScraper.jar"]
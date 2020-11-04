FROM openjdk:11

ADD ./target/     


ENTRYPOINT ["java", "-jar", "ItemCatalog-V1.jar"]

EXPOSE 8200
FROM amazoncorretto:21 as build
WORKDIR /app

RUN yum update -y && yum install -y tar gzip curl

COPY . .
RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

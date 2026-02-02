FROM eclipse-temurin:25-jdk-noble AS build

WORKDIR /root

COPY build.gradle settings.gradle gradlew ./

copy gradle ./gradle

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon || true

COPY src ./src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:25-jre-noble
WORKDIR /root

COPY --from=build /root/build/libs/konstantine-vashalomidze-website-0.0.1.jar konstantine-vashalomidze-website.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "konstantine-vashalomidze-website.jar"]


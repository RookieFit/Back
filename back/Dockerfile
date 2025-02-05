FROM gradle:8.5-jdk21 AS build

WORKDIR /app

COPY ./back/gradlew ./back/gradlew.bat ./back/gradle /app/
COPY ./back/gradle/wrapper /app/gradle/wrapper
COPY ./back/src /app/src
COPY ./back/build.gradle ./back/settings.gradle /app/

RUN chmod +x ./gradlew

RUN ./gradlew build --no-daemon --stacktrace

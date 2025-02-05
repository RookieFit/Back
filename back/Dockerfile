FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Gradle Wrapper 및 프로젝트 복사
COPY gradlew gradlew.bat build.gradle settings.gradle gradle /app/
COPY src /app/src

# 실행 권한 부여
RUN chmod +x gradlew

# Gradle 빌드 실행
RUN ./gradlew clean build --no-daemon

# 실행 환경을 위한 이미지 설정
FROM eclipse-temurin:21-jdk-alpine AS runtime

WORKDIR /app

# 빌드 단계에서 생성한 JAR 파일을 실행 환경으로 복사
COPY --from=build /app/build/libs/*.jar /app/myapp.jar

ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

EXPOSE 4040

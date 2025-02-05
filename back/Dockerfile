# 1. Gradle을 사용하여 애플리케이션을 빌드할 이미지 (빌드 단계)
FROM gradle:8.5-jdk21 AS build

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 빌드 파일 복사
COPY build.gradle settings.gradle /app/

# 4. 프로젝트의 소스 코드 복사
COPY src /app/src

# 5. Gradle 빌드 실행 (JAR 파일 생성)
RUN gradle build --no-daemon

# 6. 실행 환경을 위한 이미지 설정
FROM eclipse-temurin:21-jdk-alpine AS runtime

# 7. 작업 디렉토리 설정
WORKDIR /app

# 8. 빌드 단계에서 생성한 JAR 파일을 실행 환경으로 복사
COPY --from=build /app/build/libs/*.jar /app/myapp.jar

# 9. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

# 10. 포트 설정
EXPOSE 4040

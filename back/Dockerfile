# 1. Gradle을 사용하여 애플리케이션 빌드를 위한 베이스 이미지 설정
FROM gradle:8.5-jdk21 AS build

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle Wrapper 및 프로젝트 복사
COPY ./back/gradlew ./back/gradlew.bat ./back/gradle /app/
COPY ./back/gradle/wrapper /app/gradle/wrapper
COPY ./back/src /app/src
COPY ./back/build.gradle ./back/settings.gradle /app/

# 4. 실행 권한 부여 (권한 문제 방지)
RUN chmod +x ./gradlew

# 5. Gradle 빌드 실행 (Wrapper 사용)
RUN ./gradlew build --no-daemon

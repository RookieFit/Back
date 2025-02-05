# 1. Gradle을 사용하여 애플리케이션을 빌드할 이미지 (빌드 단계)
FROM gradle:8.5-jdk21 AS build

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle Wrapper 및 프로젝트 복사
COPY gradlew gradlew.bat build.gradle settings.gradle gradle /app/
COPY src /app/src

# 4. 실행 권한 부여 (권한 문제 방지)
RUN chmod +x gradlew

# 5. Gradle 빌드 실행 (Wrapper 사용)
RUN ./gradlew build --no-daemon

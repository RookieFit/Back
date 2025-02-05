# 1. 실행 환경 설정
FROM eclipse-temurin:21-jdk-alpine AS runtime

WORKDIR /app

# 2. 빌드된 JAR 파일 복사 (docker-build.yml에서 이미 빌드 완료된 파일)
COPY ./build/libs/*.jar /app/myapp.jar

# 3. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

# 4. 포트 노출
EXPOSE 4040
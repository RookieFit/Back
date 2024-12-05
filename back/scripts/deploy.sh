#!/bin/bash

# AWS ECR 로그인
aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 739275460428.dkr.ecr.ap-northeast-2.amazonaws.com


ECR_REGISTRY="668701699473.dkr.ecr.ap-northeast-2.amazonaws.com"

# 현재 실행 중인 애플리케이션의 포트 확인
CURRENT_PORT=$(docker ps --filter "name=rookiefit-server" --format "{{.Names}}" | grep -oE '[0-9]+$')

# 새 포트 설정
if [ "${CURRENT_PORT}" == "8080" ]; then
    TARGET_PORT=8081
else
    TARGET_PORT=8080
fi

# 새 컨테이너 실행
docker run -d --name rookiefit-server-${TARGET_PORT} -p ${TARGET_PORT}:4040 ${ECR_REGISTRY}/rookiefit-server:latest

# 새 컨테이너 상태 확인
for i in {1..10}
do
    sleep 10
    if curl -s http://localhost:${TARGET_PORT}/health > /dev/null; then
        echo "New container is running"
        break
    fi
    if [ $i -eq 10 ]; then
        echo "New container failed to start"
        exit 1
    fi
done

# Nginx 설정 업데이트
sudo sed -i "s/${CURRENT_PORT}/${TARGET_PORT}/g" /etc/nginx/sites-available/rookiefit
sudo systemctl reload nginx

# 이전 컨테이너 중지 및 제거
docker stop rookiefit-server-${CURRENT_PORT}
docker rm rookiefit-server-${CURRENT_PORT}

echo "Deployment completed"

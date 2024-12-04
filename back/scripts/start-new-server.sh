#!/bin/bash

set -e

# Docker 서비스 상태 확인
if ! systemctl is-active --quiet docker; then
  echo "Docker service is not running. Starting Docker..."
  sudo systemctl start docker
fi

# Green 환경 실행
echo "Starting new container (Green)..."
docker pull 739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest
docker run -d --name rookiefit-server-green -p 4041:4040 739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest

# 기존 Blue 컨테이너 종료
if docker ps -q -f name=rookiefit-server; then
  echo "Stopping and removing old container (Blue)..."
  docker stop rookiefit-server
  docker rm rookiefit-server
fi

# Green을 Blue로 전환
echo "Switching Green to Blue..."
docker rename rookiefit-server-green rookiefit-server

# 최종 확인
echo "Deployment completed successfully."
docker ps

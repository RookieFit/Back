#!/bin/bash

set -e

# Docker 서비스 상태 확인 및 시작
if ! systemctl is-active --quiet docker; then
  echo "Docker service is not running. Starting Docker..."
  sudo systemctl start docker
fi

# 새로운 Green 컨테이너 실행
echo "Starting new container (Green)..."
docker pull 739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest
docker run -d --name rookiefit-server-green -p 4041:4040 739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest

# Green 컨테이너 헬스 체크 (필요 시 추가)
# echo "Health checking Green container..."
# curl 또는 Docker의 헬스 체크 기능을 활용하여 Green 상태 확인.

# 기존 Blue 컨테이너 종료 및 제거
if docker ps -q -f name=rookiefit-server > /dev/null; then
  echo "Stopping and removing old container (Blue)..."
  docker stop rookiefit-server
  docker rm rookiefit-server
fi

# Green 컨테이너를 Blue로 전환
echo "Switching Green to Blue..."
docker rename rookiefit-server-green rookiefit-server

# 배포 완료 메시지 및 상태 확인
echo "Deployment completed successfully."
docker ps

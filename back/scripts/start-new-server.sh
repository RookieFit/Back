#!/bin/bash

# Docker 서비스 상태 확인
if ! systemctl is-active --quiet docker; then
  echo "Docker service is not running. Starting Docker..."
  sudo systemctl start docker
fi

# 기존 컨테이너가 실행 중인지 확인
if [ $(docker ps -q -f name=rookiefit-server) ]; then
  echo "Stopping existing container..."
  docker stop rookiefit-server
  docker rm rookiefit-server
fi

# 새로운 컨테이너 실행
docker run -d --name rookiefit-server-green -p 4041:4040 ${{ steps.login-ecr.outputs.registry }}/rookiefit-server:latest

# 기존 컨테이너를 멈추지 않고 유지
docker stop rookiefit-server || true
docker rm rookiefit-server || true

# Green 환경을 Blue로 전환
docker rename rookiefit-server-green rookiefit-server

# 기존 컨테이너를 재시작하여 최신 버전 반영
docker restart rookiefit-server

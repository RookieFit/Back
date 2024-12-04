#!/bin/bash

set -e

echo "--------------- 서버 배포 시작 -----------------"

# 기존 컨테이너 중지 및 제거
docker stop RookieFit || true
docker rm RookieFit || true

# 최신 이미지를 Pull
docker pull 739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest

# 새로운 컨테이너 실행
docker run -d --name RookieFit -p 4040:4040 \
  --health-cmd="curl -f http://localhost:4040/actuator/health || exit 1" \
  --health-interval=30s \
  --health-timeout=10s \
  --health-retries=3 \
  739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest

echo "--------------- 서버 배포 끝 -----------------"

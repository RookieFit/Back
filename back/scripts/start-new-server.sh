#!/bin/bash

set -e

echo "--------------- Green 컨테이너 배포 시작 -----------------"

# Docker 서비스 상태 확인
if ! systemctl is-active --quiet docker; then
  echo "Docker 서비스가 실행 중이 아닙니다. Docker를 시작합니다..."
  sudo systemctl start docker
fi

# Green 컨테이너 실행
docker pull 739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest

docker run -d --name rookiefit-server-green -p 4041:4040 \
  --health-cmd="curl -f http://localhost:4040/actuator/health || exit 1" \
  --health-interval=30s \
  --health-timeout=10s \
  --health-retries=3 \
  739275460428.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest

# Green 컨테이너 헬스 체크
echo "헬스 체크 중..."
GREEN_HEALTH_CHECK_STATUS=1
for i in {1..10}; do
  if docker inspect --format='{{json .State.Health.Status}}' rookiefit-server-green | grep -q "healthy"; then
    GREEN_HEALTH_CHECK_STATUS=0
    break
  fi
  echo "헬스 체크 실패. 다시 시도 중... (${i}/10)"
  sleep 5
done

if [ "$GREEN_HEALTH_CHECK_STATUS" -ne 0 ]; then
  echo "Green 컨테이너가 헬스 체크를 통과하지 못했습니다. 배포를 중단합니다."
  docker logs rookiefit-server-green
  exit 1
fi

# 기존 Blue 컨테이너 종료
if docker ps -q -f name=rookiefit-server > /dev/null; then
  echo "기존 Blue 컨테이너 중지 및 제거..."
  docker stop rookiefit-server
  docker rm rookiefit-server
fi

# Green 컨테이너를 Blue로 전환
echo "Green 컨테이너를 Blue로 전환..."
docker rename rookiefit-server-green rookiefit-server

echo "--------------- 배포 완료 -----------------"
docker ps

#!/bin/bash

# 서버에서 Docker 이미지 pull
docker pull pipakmj/myapp:latest

# 기존 컨테이너 종료 및 삭제
docker stop myapp_container || true
docker rm myapp_container || true

# 새로운 컨테이너 실행
docker run -d --name myapp_container -p 4040:4040 pipakmj/myapp:latest
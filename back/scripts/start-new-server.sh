# 새로운 버전의 컨테이너 실행 (Green 환경)
docker run -d --name rookiefit-server-green -p 4041:4040 ${{ steps.login-ecr.outputs.registry }}/rookiefit-server:latest

# 기존의 컨테이너를 멈추지 않고 유지
docker stop rookiefit-server || true
docker rm rookiefit-server || true

# Green 환경을 Blue로 전환
docker rename rookiefit-server-green rookiefit-server

# 기존의 컨테이너를 재시작하여 최신 버전 반영
docker restart rookiefit-server

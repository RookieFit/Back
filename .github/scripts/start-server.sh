echo "--------------- 서버 배포 시작 -----------------"
docker stop instagram-server || true
docker rm instagram-server || true
docker pull {ECR Repository 주소}/instagram-server:latest
docker run -d --name instagram-server -p 3000:3000 {ECR Repository 주소}/instagram-server:latest
echo "--------------- 서버 배포 끝 ------------------"

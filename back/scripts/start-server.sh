echo "--------------- 서버 배포 시작 -----------------"
docker stop RookieFit || true
docker rm RookieFit || true
docker pull 668701699473.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest
docker run -d --name RookieFit -p 3000:3000 668701699473.dkr.ecr.ap-northeast-2.amazonaws.com/rookiefit-server:latest
echo "--------------- 서버 배포 끝 -----------------"
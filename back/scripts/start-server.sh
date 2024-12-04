echo "--------------- 서버 배포 시작 -----------------"
docker stop nodejs-git-github || true
docker rm nodejs-git-github || true
docker pull 668701699473.dkr.ecr.ap-northeast-2.amazonaws.com/pipa-nodejs-git-github-repository:latest
docker run -d --name nodejs-git-github -p 3000:3000 668701699473.dkr.ecr.ap-northeast-2.amazonaws.com/pipa-nodejs-git-github-repository:latest
echo "--------------- 서버 배포 끝 -----------------"
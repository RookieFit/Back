#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
cd /home/ubuntu/instagram-server
npm i
pm2 kill
pm2 start dist/main.js --name "backend-server"
echo "--------------- 서버 배포 끝 -----------------"

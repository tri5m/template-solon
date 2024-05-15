#!/bin/bash
mvn -skipTest=true clean package
imageName="template-solon-service"
version="latest"
port="9090"
echo "构建镜像:"${imageName}:${version}
docker stop ${imageName}
docker rm ${imageName}
docker rmi ${imageName}:${version}
docker build -f ./Dockerfile --rm -t ${imageName}:${version} .
echo "构建完成."

echo "启动中..."
docker run --name ${imageName} -d -p ${port}:${port} \
--memory-reservation=512M --memory=512M --oom-kill-disable=true \
-v /tmp/logs/${imageName}/${port}/logs:/app/logs
${imageName}:${version} --env=test

#--network=test-net --ip=172.20.0.6
echo "启动完成."
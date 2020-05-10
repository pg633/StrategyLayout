#!/bin/bash
function mvn_com() {
  cd ..
  mvn clean package  -Dmaven.test.skip=true
}

function init_mysql() {
  docker search mysql
  docker pull mysql
  docker images
  docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456789 -d mysql
}
function init_tair() {
  docker search redis
  docker pull redis

  docker run -p 6379:6379 -d redis:latest redis-server
# 链接远端
# docker exec -it redis_s redis-cli -h 192.168.1.100 -p 6379 -a your_password
 
}
function text_redis() {

  redis_contain=`docker ps -a  | grep redis | head -n  1 | awk  '{print $1}'`
  echo '获取docker redis ip' + $redis_contain
  docker exec -ti ${redis_contain} redis-cli -h localhost -p 6379
  #  docker exec -it redis_s redis-cli -h 192.168.1.100 -p 6379 -a your_password //如果有密码 使用 -a参数
}

text_redis
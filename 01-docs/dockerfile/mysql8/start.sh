#!/bin/bash
cur_dir=`pwd`

docker stop saas_mysql
docker rm saas_mysql
docker run --name saas_mysql --restart=always \
    -v `pwd`/conf:/etc/mysql/conf.d \
    -v /data/docker-data/mysql8-data/:/var/lib/mysql \
    -p 3218:3306 \
    -e MYSQL_ROOT_PASSWORD="root" \
    -e TZ=Asia/Shanghai \
    -d mysql:8.0.19

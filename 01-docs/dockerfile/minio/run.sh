#!/bin/bash
cur_dir=`pwd`

docker stop saas_minio
docker rm saas_minio
docker run -p 9000:9000 --name saas_minio --restart=always \
  -d minio/minio server /data
#  -v /mnt/data:/data \
#  -v ${cur_dir}/config:/root/.minio \

#!/usr/bin/env bash
#REGISTRY=registry.cn-hangzhou.aliyuncs.com
REPOSITORY=wuzhike/paperless-meeting-system  # This is the one mostly we need to change for different project
TAG=latest

cd /home/sino-box/paperless-meeting-system
docker-compose down
docker rmi -f $(docker images $REPOSITORY:$TAG -q)
docker-compose up -d
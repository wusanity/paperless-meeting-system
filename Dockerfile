# 该镜像需要依赖的基础镜像
FROM openjdk:8-jre
# 将当前目录下的jar包复制到docker容器的/目录下
ADD paperless-meeting-system.jar /paperless-meeting-system.jar
# 运行过程中创建一个paperless-meeting-system.jar文件
RUN /bin/sh -c 'touch /paperless-meeting-system.jar'
# 声明服务运行在9070端口
VOLUME ["/usr/digital/comppose/docker-images-location-video"]
EXPOSE 19000
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","-Dspring.config.additional-location=/usr/digital/compose/docker-images-location-video/application.properties","-Dlog.path=/var/log/locationvideo-service","/paperless-meeting-system.jar"]
# 指定维护者的名字
MAINTAINER wuzhike
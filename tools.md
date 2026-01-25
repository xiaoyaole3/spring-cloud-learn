# Consul 使用 docker 进行安装

```docker
docker run --name=consul-server -d -p 8500:8500 -p 8600:8600/udp hashicorp/consul:1.21.3 consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0 -data-dir=/consul/data
```

# Zipkin 使用 docker 进行安装

```docker
docker run -d -p 9411:9411 --name zipkin  openzipkin/zipkin:v3.0.0
```

# docker 搭建Nacos

安装前，需要在mysql数据库中创建对应的数据库，以及根据初始化脚本对于数据库进行初始化。可以参考init.sql

```docker
docker run -d -p 8848:8848 -p 9848:9848 -p 9849:9849 \
--name nacos \
-e MODE=standalone \
-e NACOS_AUTH_ENABLE=true \
-e NACOS_AUTH_TOKEN=840hCKEPb1Y7aLh8JOLuTKDIq2cNuyp4uSLGmwLS+BU= \
-e NACOS_AUTH_IDENTITY_KEY=nacos \
-e NACOS_AUTH_IDENTITY_VALUE=nacos \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=127.0.0.1 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=123456 \
-e MYSQL_SERVICE_DB_NAME=nacos_config \
-e MYSQL_SERVICE_PORT=33061 \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e JVM_XMN=256m \
--network host \
nacos/nacos-server:v2.3.2-slim
```

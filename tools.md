# Consul 使用 docker 进行安装

```docker
docker run --name=consul-server -d -p 8500:8500 -p 8600:8600/udp hashicorp/consul:1.21.3 consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0 -data-dir=/consul/data
```

# Zipkin 使用 docker 进行安装

```shell
docker run -d -p 9411:9411 --name zipkin  openzipkin/zipkin:v3.0.0
```

# docker 搭建Nacos

安装前，需要在mysql数据库中创建对应的数据库，以及根据初始化脚本对于数据库进行初始化。可以参考init.sql

```shell
docker run -d -p 8848:8848 -p 9848:9848 -p 9849:9849 \
--name nacos \
-e MODE=standalone \
-e NACOS_AUTH_ENABLE=true \
-e NACOS_AUTH_TOKEN=840hCKEPb1Y7aLh8JOLuTKDIq2cNuyp4uSLGmwLS+BU= \
-e NACOS_AUTH_IDENTITY_KEY=nacos \
-e NACOS_AUTH_IDENTITY_VALUE=nacos \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=192.168.31.191 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=123456 \
-e MYSQL_SERVICE_DB_NAME=nacos_config \
-e MYSQL_SERVICE_PORT=33061 \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e JVM_XMN=256m \
nacos/nacos-server:v2.3.2-slim
```

# docker 搭建 seata-server

## 下载镜像
```shell
docker pull apache/seata-server:2.2.0-slim
```
## 创建一个临时容器，copy配置文件
```shell
docker run -d --name seata-server -p 8091:8091 -p 7091:7091 apache/seata-server:2.2.0-slim
```
```shell
docker cp seata-server:/seata-server/resources /Users/wander/docker_data/seata/config
```

## 修改配置文件信息
```yaml
seata:
  config:
    # support: nacos, consul, apollo, zk, etcd3
    type: nacos
    nacos:
      server-addr: 192.168.31.191:8848
      namespace: 
      group: SEATA_GROUP # 需要在Nacos中新建SEATA_GROUP
      username: nacos
      password: nacos
      data-id: seataServer.properties    
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos
    nacos:
      application: seata-server
      server-addr: 192.168.31.191:8848
      namespace: 
      cluster: default
      group: SEATA_GROUP
      username: nacos
      password: nacos
      data-id: seataServer.properties      
  store:
    # support: file 、 db 、 redis 、 raft
    mode: db
    db:
      datasource: druid
      db-type: mysql
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://192.168.31.191:33061/seata?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true
      user: root
      password: 123456
      min-conn: 10
      max-conn: 100
      global-table: global_table
      branch-table: branch_table
      lock-table: lock_table
      distributed-lock-table: distributed_lock
      vgroup-table: vgroup_table
      query-limit: 1000
      max-wait: 5000       
```


## 指定配置文件运行
```shell
docker run --name seata-server \
-p 8091:8091 \
-p 7091:7091 \
-v /Users/wander/docker_data/seata/config:/seata-server/resources  \
apache/seata-server:2.2.0-slim
```

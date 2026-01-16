# Consul 使用 docker 进行安装

```docker
docker run --name=consul-server -d -p 8500:8500 -p 8600:8600/udp hashicorp/consul:1.21.3 consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0 -data-dir=/consul/data
```
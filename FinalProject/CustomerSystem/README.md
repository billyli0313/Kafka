# Asynchronous flow & kafka

### Reference Documentation

### How Asynchronous flow works?

### What’s messaging queue?

### What is Kafka?
a queue to receive events from one service to another 

### zookeeper(similar to eureka) 
manage your kafka send message to the destination and check your kafka status during entry -> exit 

### How to use Kafka?
1. download binary package: https://www.conduktor.io/kafka/how-to-install-apache-kafka-on-windows
2. start the zookeeper by cmd: bin/zookeeper-server-start.sh config/zookeeper.properties
```
cd c:\kafka
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

3. start kafka service by cmd: bin/kafka-server-start.sh config/server.properties
```
cd c:\kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
Producer -> Queue -> Consumer
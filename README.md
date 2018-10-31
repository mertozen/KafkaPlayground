# KafkaPlayground
# Create Topic
* kafka-topics --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 3 --replication-factor 1
# List topic
* kafka-topics --zookeeper 127.0.0.1:2181 --list
# Describe topic
* kafka-topics --zookeeper 127.0.0.1:2181 --describe
# Delete topic
* kafka-topics --zookeeper 127.0.0.1:2181 --topic second_topic --delete
# Producer console for first_topic
* kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic
kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic --producer-property acks=all
# Automatic create topic when execute producer console command default num.partitions = 1 in server.properties conf
* kafka-console-producer --broker-list 127.0.0.1:9092 --topic new_topic
# consume messages
* kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic
# consume messages from beginning 
* kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --from-beginning
# consumer group
* kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-application --from-beginning
# list consumer groups
* kafka-consumer-groups --bootstrap-server localhost:9092 --list
# describe consumer groups
* kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-first-application
# reset offsets
* kafka-consumer-groups --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest --execute --topic first-topic
# shift offsets 
kafka-consumer-groups --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --shift-by 2 --execute --topic first-topic

package com.kafkademo.kafka_demo;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoKeys {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

		String bootstrapServers = "127.0.0.1:9092";
		// create Producer properties
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// create the producer

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		for (int i = 0; i < 10; i++) {

			String topic = "first_topic";
			String value = "hello world " + Integer.toString(i);
			String key = "id_" + Integer.toString(i);

			// create a producer record
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);

			logger.info("Key: " + key); // log the key
			
			
			// send data - asynchronous
			producer.send(record, new Callback() {

				public void onCompletion(RecordMetadata metadata, Exception exception) {
					// executes every time a record is successfully or an exception is thrown

					if (exception == null) {
						// the record was successfully sent
						logger.info("Received new metadata. \n" + "Topic: " + metadata.topic() + "\n" + "Partition: "
								+ metadata.partition() + "\n" + "Offset: " + metadata.offset() + "\n" + "Timestamp: "
								+ metadata.timestamp());
					} else {

						logger.error("Error while producing", exception);
					}

				}
			}).get(); // block the .send() to make it synchronous - don't do this production!
		}
		producer.flush();

		producer.close();

	}

}

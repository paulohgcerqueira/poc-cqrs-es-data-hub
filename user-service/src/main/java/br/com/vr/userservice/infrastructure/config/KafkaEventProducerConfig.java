package br.com.vr.userservice.infrastructure.config;

import javassist.bytecode.ByteArray;
import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.eventhandling.producer.ConfirmationMode;
import org.axonframework.extensions.kafka.eventhandling.producer.DefaultProducerFactory;
import org.axonframework.extensions.kafka.eventhandling.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class KafkaEventProducerConfig {

    /**
     * Creates a Kafka producer factory, using the Kafka properties configured in resources/application.yml
     */
    @Bean
    public ProducerFactory producerFactory(KafkaProperties kafkaProperties){
        return DefaultProducerFactory.builder()
                .configuration(kafkaProperties.buildProducerProperties())
                .producerCacheSize(10_000)
                .confirmationMode(ConfirmationMode.WAIT_FOR_ACK)
                .build();
    }
}

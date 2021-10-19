package br.com.vr.datahubservice.infrastructure.config;

import org.axonframework.config.Configurer;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.autoconfig.KafkaAutoConfiguration;
import org.axonframework.extensions.kafka.configuration.KafkaMessageSourceConfigurer;
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.consumer.ConsumerFactory;
import org.axonframework.extensions.kafka.eventhandling.consumer.Fetcher;
import org.axonframework.extensions.kafka.eventhandling.consumer.subscribable.SubscribableKafkaMessageSource;
import org.axonframework.springboot.autoconfig.AxonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class KafkaEventConsumptionConfig {

    @Bean
    public KafkaMessageSourceConfigurer registerKafkaMessageSourceConfigurer(Configurer configurer) {
        KafkaMessageSourceConfigurer kafkaMessageSourceConfigurer = new KafkaMessageSourceConfigurer();
        configurer.registerModule(kafkaMessageSourceConfigurer);
        return kafkaMessageSourceConfigurer;
    }

    @Bean
    public SubscribableKafkaMessageSource<String, byte[]> subscribableKafkaMessageSource(KafkaProperties kafkaProperties,
                                                                                         ConsumerFactory<String, byte[]> consumerFactory,
                                                                                         Fetcher<String, byte[], EventMessage<?>> fetcher,
                                                                                         KafkaMessageSourceConfigurer kafkaMessageSourceConfigurer) {
        SubscribableKafkaMessageSource<String, byte[]> subscribableKafkaMessageSource = SubscribableKafkaMessageSource.<String, byte[]>builder()
                .topics(Arrays.asList(kafkaProperties.getDefaultTopic())) // Defaults to a collection of "Axon.Events"
                .groupId("userservice-group")       // Hard requirement
                .consumerFactory(consumerFactory)   // Hard requirement
                .fetcher(fetcher)                   // Hard requirement
                .build();
        // Registering the source is required to tie into the Configurers lifecycle to start the source at the right stage
        kafkaMessageSourceConfigurer.configureSubscribableSource(configuration -> subscribableKafkaMessageSource);
        return subscribableKafkaMessageSource;
    }

}

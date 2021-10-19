package br.com.vr.orderservice.infrastructure.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class MongoDBConfig {

    private final MongoProperties properties;

    public MongoDBConfig(MongoProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MongoClient mongo() throws IOException {
        MongoCredential credential = MongoCredential.createCredential(properties.getUsername(), properties.getDatabase(), properties.getPassword());

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToSocketSettings(builder -> builder.readTimeout(10, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS))
                .applyToClusterSettings(builder ->
                        builder.hosts(Arrays.asList(new ServerAddress(properties.getHost(), properties.getPort()))))
                .build();

        return MongoClients.create(settings);
    }
}

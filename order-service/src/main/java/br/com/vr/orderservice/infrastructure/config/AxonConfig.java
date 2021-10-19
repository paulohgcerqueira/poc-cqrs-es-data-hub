package br.com.vr.orderservice.infrastructure.config;

import com.mongodb.client.MongoClient;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }

    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return  MongoEventStorageEngine.builder().mongoTemplate(
                DefaultMongoTemplate.builder().mongoDatabase(client).build()
        ).build();
    }

    @Bean
    XStream xstream(){
        XStream xstream = new XStream();
        // clear out existing permissions and set own ones
        xstream.addPermission(NoTypePermission.NONE);
        // allow any type from the same package
        xstream.allowTypesByWildcard(new String[] {
                "br.com.vr.**",
                "org.axonframework.**",
                "org.springframework.**",
                "lombok.**",
                "java.**",
                "com.thoughtworks.xstream.**"
        });

        return xstream;
    }

    @Bean
    @Primary
    public Serializer serializer(XStream xStream) {
        return XStreamSerializer.builder().xStream(xStream).build();
    }

    @Bean(name = "axonTokenStore")
    public TokenStore axonTokenStore(MongoClient client) {
        Serializer tokenSerializer = XStreamSerializer.builder().build();

        return MongoTokenStore.builder()
                .serializer(tokenSerializer)
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build())
                .build();
    }

}

PoC dedicada a validar o uso do CQRS e Event Sourcing para Data Aggregator, usando o Axon Framework, MongoDB como EventStore e Read Database

### Pré-Requisitos
    Java 8
    Maven 3+
    Docker    
    
### Docker Compose
    O docker compose contém todas as depêndencias externas para execução da PoC.
    Para execução utilizar "docker compose up"
    Obs.: Caso necessário eliminar os volumes anônimos, executar com o parâmetro -V
    
    **Serviços**:
        **MongoDB** como EventStore
        **cp-enterprise-kafka** como EventBroker
        **cp-zookeeper** como dependência do Kafka para gestão de configurações centralizadas.
        **cp-enterprise-control-center** como interface web para gerenciamento e monitoramento do confluent platform
    
##### MongoDB (EventStore) & Kafka (EventBus)
    Para utilização do MongoDB como EventStore, você pode rodar como preferir (ajustando as configurações nas aplicações)
    Mas para facilitar já esta disponivel no docker-compose um mongodb como serviço configurado de acordo com as aplicações da PoC.
    Para visualização adminstrativa da instância, você pode utilizar qualquer ferramente, como por exemplo o Robo 3T (https://robomongo.org/download).
        
##### Confluent Kafka, Confluent ControlCenter
    **Criação de topicos** 
        bin\kafka-topics.sh --create --topic userservice-topic --replication-factor 1 --partitions 2  --zookeeper localhost:2181
        bin\kafka-topics.sh --create --topic orderservice-topic --replication-factor 1 --partitions 2  --zookeeper localhost:2181
    Obs.: Com a configuração no serviço do kafka no docker compose para KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'true', irá permitir que os topics
    sejam criados em runtime.
        
    **Visualizar via Control Center**   
        http://localhost:9021/

##### Serviços (Componentes)        
    **DataHub**
        Swagger: http://localhost:8080/swagger-ui.html
        H2 Database: http://localhost:8080/h2-console
    
    **User Service**
        Swagger: http://localhost:8081/swagger-ui.html
        H2 Database: http://localhost:8081/h2-console
        
    **Order Service**
        Swagger: http://localhost:8082/swagger-ui.html
        H2 Database: http://localhost:8082/h2-console
   
##### Axon Framework
    O Axon é um framework open source para construção de aplicações java/kotlin para arquitetura orientada à eventos, 
    aplicando o Design Pattern CQRS baseado no DDD. 
    Referências:
        https://axoniq.io/resources/concepts 
        https://docs.axoniq.io/reference-guide/v/3.0/
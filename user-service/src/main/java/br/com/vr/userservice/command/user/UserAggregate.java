package br.com.vr.userservice.command.user;


import br.com.vr.events.UserAddressUpdatedEvent;
import br.com.vr.events.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String postcode;

    public UserAggregate() {}

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand) {
        log.info("Handling command {} : {}", createUserCommand.getClass().getSimpleName(), createUserCommand);

        createUserCommandValidation(createUserCommand);

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(createUserCommand, userCreatedEvent);

        apply(userCreatedEvent);
        log.info("Done handling command {} : {}", createUserCommand.getClass().getSimpleName(), createUserCommand);
    }

    @CommandHandler
    public void handle(UpdateAddressUserCommand updateAddressUserCommand) {
        log.info("Handling command {} : {}", updateAddressUserCommand.getClass().getSimpleName(), updateAddressUserCommand);

        updateUserCommandValidation(updateAddressUserCommand);

        UserAddressUpdatedEvent userAddressUpdatedEvent = new UserAddressUpdatedEvent();
        BeanUtils.copyProperties(updateAddressUserCommand, userAddressUpdatedEvent);

        apply(userAddressUpdatedEvent);
        log.info("Done handling command {} : {}", updateAddressUserCommand.getClass().getSimpleName(), updateAddressUserCommand);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        log.info("Handling event {} : {}", event.getClass().getSimpleName(), event);
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        log.info("Done handling event {} : {}", event.getClass().getSimpleName(), event);
    }

    @EventSourcingHandler
    public void on(UserAddressUpdatedEvent event) {
        log.info("Handling event {} : {}", event.getClass().getSimpleName(), event);
        this.userId = event.getUserId();
        this.city = event.getCity();
        this.postcode = event.getPostcode();
        this.state = event.getState();
        log.info("Done handling event {} : {}", event.getClass().getSimpleName(), event);
    }

    public void createUserCommandValidation(CreateUserCommand createUserCommand) throws IllegalArgumentException {
        if(createUserCommand.getFirstName().isEmpty()){
            throw new IllegalArgumentException("O campo firstName é obrigatório");
        }else if(createUserCommand.getLastName().isEmpty()){
            throw new IllegalArgumentException("O campo lastName é obrigatório");
        }
    }

    public void updateUserCommandValidation(UpdateAddressUserCommand updateAddressUserCommand) throws IllegalArgumentException {
        if(updateAddressUserCommand.getCity().isEmpty()){
            throw new IllegalArgumentException("O campo city é obrigatório");
        }else if(updateAddressUserCommand.getPostcode().isEmpty()){
            throw new IllegalArgumentException("O campo postcode é obrigatório");
        }else if(updateAddressUserCommand.getState().isEmpty()){
            throw new IllegalArgumentException("O campo state é obrigatório");
        }
    }

    @Bean
    public Repository<UserAggregate> repository(EventStore eventStore) {
        final EventSourcingRepository<UserAggregate> userAggregateEventSourcingRepository = EventSourcingRepository.builder(UserAggregate.class).eventStore(eventStore).build();
        return userAggregateEventSourcingRepository;

    }
}

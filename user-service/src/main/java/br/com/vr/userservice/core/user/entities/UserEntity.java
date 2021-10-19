package br.com.vr.userservice.core.user.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@Data
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postcode")
    private String postcode;

}

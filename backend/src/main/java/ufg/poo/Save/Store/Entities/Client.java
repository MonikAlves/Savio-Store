package ufg.poo.Save.Store.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Client Entity
 *
 */
@Entity
@Table(name="client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client implements Serializable {
    /**
     * The indentifier of the client
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of the client
     */
    @Column(nullable = false)
    private String name;

    /**
     * The email of the client
     */
    @Column(nullable = false)
    private String email;

    /**
     * The phone of the client
     */
    @Column(nullable = false)
    private String phone;

    /**
     * The password of the client
     */
    @Column(nullable = false)
    private String password;

    /**
     * The legal data of the client
     */
    @Column(nullable = false)
    private String legalData;

    /**
     * If client is super user
     */
    @Column(nullable = false)
    private boolean superUser;

}

package ufg.poo.Save.Store.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String legalData;

    @Column(nullable = false)
    private boolean superUser;


}

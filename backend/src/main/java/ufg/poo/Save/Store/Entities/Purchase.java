package ufg.poo.Save.Store.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private LocalDate date;
}

package ufg.poo.Save.Store.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Purchase Entity
 *
 */
@Entity
@Table(name="purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Purchase implements Serializable {
    /**
     * The indentifier of the purchase
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The product of the purchase
     */
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false)
    private Product product;

    /**
     * The client of the purchase
     */
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    /**
     * The product quantity of the purchase
     */
    @Column(nullable = false)
    private int quantity;

    /**
     * The product size of the purchase
     */
    @Column(nullable = false)
    private String size;

    /**
     * The total value of the purchase
     */
    @Column(nullable = false)
    private double total;

    /**
     * The local date of the purchase
     */
    @Column(nullable = false)
    private LocalDate date;
}

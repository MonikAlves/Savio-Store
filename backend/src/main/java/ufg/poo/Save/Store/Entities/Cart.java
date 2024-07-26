package ufg.poo.Save.Store.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Cart Entity
 *
 */
@Entity
@Table(name="cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart implements Serializable {
    /**
     * The indentifier of the cart
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The product of the cart
     */
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false)
    private Product product;

    /**
     * The client of the cart
     */
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    /**
     * The quantity of the product
     */
    @Column(nullable = false)
    private int quantity;

    /**
     * The size of the product
     */
    @Column(nullable = false)
    private String size;

    /**
     * The availability of the cart procut
     */
    @Column(nullable = false)
    private boolean available;

    /**
     * The total value of the cart
     */
    @Column(nullable = false)
    private double total;
}

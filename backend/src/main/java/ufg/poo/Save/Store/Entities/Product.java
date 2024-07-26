package ufg.poo.Save.Store.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Product Entity
 *
 */
@Entity
@Table(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    /**
     * The indentifier of the product
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of the product
     */
    @Column(nullable = false)
    private String name;

    /**
     * The description of the product
     */
    @Column(nullable = false)
    private String description;

    /**
     * The size of the product
     */
    @Column(nullable = false)
    private String size;

    /**
     * The price of the product
     */
    @Column(nullable = false)
    private Double price;

    /**
     * The stock of the product
     */
    @Column(nullable = false)
    private String stock;

    /**
     * The path of the product image
     */
    @Column(nullable = false)
    private String image;

}

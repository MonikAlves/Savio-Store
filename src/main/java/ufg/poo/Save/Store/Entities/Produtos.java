package ufg.poo.Save.Store.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="produto")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Produtos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private int estoque;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String tamanho;

}

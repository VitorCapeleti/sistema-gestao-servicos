package br.com.sgs.sistema_gestao_servicos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: Cria getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Cria um construtor sem argumentos
@AllArgsConstructor // Lombok: Cria um construtor com todos os argumentos
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;


    // Getters and Setters
}

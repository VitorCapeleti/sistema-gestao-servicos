package br.com.sgs.sistema_gestao_servicos.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgs.sistema_gestao_servicos.model.Cliente;

@Repository // Spring: Indica que esta é uma classe de acesso a dados (opcional, mas boa prática)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Exemplo de consulta customizada:
    Optional<Cliente> findByEmail(String email);
}
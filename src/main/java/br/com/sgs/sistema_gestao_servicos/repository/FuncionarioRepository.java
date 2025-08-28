package br.com.sgs.sistema_gestao_servicos.repository;

import br.com.sgs.sistema_gestao_servicos.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
    // O Spring Data JPA já nos dá o CRUD. Podemos adicionar consultas customizadas se precisarmos.
    Optional<Funcionario> findByEmail(String email);
}
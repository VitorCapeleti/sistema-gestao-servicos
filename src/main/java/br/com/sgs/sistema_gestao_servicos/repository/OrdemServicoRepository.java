package br.com.sgs.sistema_gestao_servicos.repository;

import br.com.sgs.sistema_gestao_servicos.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
package br.com.sgs.sistema_gestao_servicos.service;

import br.com.sgs.sistema_gestao_servicos.dto.OrdemServicoRequestDTO;
import br.com.sgs.sistema_gestao_servicos.enums.StatusOS;
import br.com.sgs.sistema_gestao_servicos.model.Cliente;
import br.com.sgs.sistema_gestao_servicos.model.Funcionario;
import br.com.sgs.sistema_gestao_servicos.model.OrdemServico;
import br.com.sgs.sistema_gestao_servicos.repository.ClienteRepository;
import br.com.sgs.sistema_gestao_servicos.repository.FuncionarioRepository;
import br.com.sgs.sistema_gestao_servicos.repository.OrdemServicoRepository;
import br.com.sgs.sistema_gestao_servicos.service.event.OrdemServicoStatusEvent;
import br.com.sgs.sistema_gestao_servicos.service.strategy.CalculoCustoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired private OrdemServicoRepository osRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private FuncionarioRepository funcionarioRepository;
    @Autowired private ApplicationEventPublisher eventPublisher;
    @Autowired private Map<String, CalculoCustoStrategy> strategyMap;

    // Padrão FACTORY METHOD: Responsável por criar e configurar uma nova OS.
    public OrdemServico abrirOS(OrdemServicoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        OrdemServico os = new OrdemServico();
        os.setCliente(cliente);
        os.setFuncionario(funcionario);
        os.setTipo(dto.getTipo());
        os.setDescricao(dto.getDescricao());
        os.setStatus(StatusOS.ABERTA);
        os.setDataAbertura(LocalDateTime.now());

        return osRepository.save(os);
    }

    // Padrão OBSERVER: Notifica a mudança de status.
    public OrdemServico atualizarStatus(Long id, StatusOS novoStatus) {
        OrdemServico os = osRepository.findById(id).orElseThrow(() -> new RuntimeException("OS não encontrada"));
        os.setStatus(novoStatus);
        
        if (novoStatus == StatusOS.CONCLUIDA || novoStatus == StatusOS.CANCELADA) {
            os.setDataFechamento(LocalDateTime.now());
        }

        OrdemServico osSalva = osRepository.save(os);
        eventPublisher.publishEvent(new OrdemServicoStatusEvent(this, osSalva)); // Publica o evento!
        return osSalva;
    }
    
    // Padrão STRATEGY: Usa a estratégia correta para calcular o custo.
    public OrdemServico finalizarOS(Long id) {
        OrdemServico os = osRepository.findById(id).orElseThrow(() -> new RuntimeException("OS não encontrada"));

        CalculoCustoStrategy strategy = strategyMap.get(os.getTipo().name());
        if(strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de custo encontrada para o tipo: " + os.getTipo());
        }
        
        BigDecimal custoFinal = strategy.calcular();
        os.setCusto(custoFinal);
        
        // Finaliza chamando o método que já dispara o evento do Observer
        return this.atualizarStatus(id, StatusOS.CONCLUIDA);
    }

    public List<OrdemServico> listarTodas() {
        return osRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Long id) {
        return osRepository.findById(id);
    }
}
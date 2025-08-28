package br.com.sgs.sistema_gestao_servicos.service;

import br.com.sgs.sistema_gestao_servicos.model.Cliente;
import br.com.sgs.sistema_gestao_servicos.repository.ClienteRepository;
import br.com.sgs.sistema_gestao_servicos.service.event.ClienteCriadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher; // Injetamos o publicador de eventos

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente criarNovoCliente(Cliente novoCliente) {
        // 1. Salva o cliente no banco de dados
        Cliente clienteSalvo = clienteRepository.save(novoCliente);

        // 2. Publica o evento! O Spring vai chamar todos os Listeners automaticamente
        eventPublisher.publishEvent(new ClienteCriadoEvent(this, clienteSalvo));
        
        return clienteSalvo;
    }

    public Cliente atualizarCliente(Long id, Cliente clienteDetalhes) {
        // Busca o cliente existente ou lança uma exceção se não encontrar
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        // Atualiza os campos do cliente existente com os novos detalhes
        clienteExistente.setNome(clienteDetalhes.getNome());
        clienteExistente.setEmail(clienteDetalhes.getEmail());
        clienteExistente.setTelefone(clienteDetalhes.getTelefone());

        // Salva e retorna o cliente atualizado
        return clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(Long id) {
        // Verifica se o cliente existe antes de deletar
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com o id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
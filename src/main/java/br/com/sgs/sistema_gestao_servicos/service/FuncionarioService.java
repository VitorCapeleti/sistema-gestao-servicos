package br.com.sgs.sistema_gestao_servicos.service;

import br.com.sgs.sistema_gestao_servicos.model.Funcionario;
import br.com.sgs.sistema_gestao_servicos.repository.FuncionarioRepository;
import br.com.sgs.sistema_gestao_servicos.service.event.FuncionarioCriadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario criarNovoFuncionario(Funcionario novoFuncionario) {
        Funcionario funcionarioSalvo = funcionarioRepository.save(novoFuncionario);
        eventPublisher.publishEvent(new FuncionarioCriadoEvent(this, funcionarioSalvo));
        return funcionarioSalvo;
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioDetalhes) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o id: " + id));

        funcionarioExistente.setNome(funcionarioDetalhes.getNome());
        funcionarioExistente.setCargo(funcionarioDetalhes.getCargo());
        funcionarioExistente.setEmail(funcionarioDetalhes.getEmail());

        return funcionarioRepository.save(funcionarioExistente);
    }

    public void deletarFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado com o id: " + id);
        }
        funcionarioRepository.deleteById(id);
    }
}
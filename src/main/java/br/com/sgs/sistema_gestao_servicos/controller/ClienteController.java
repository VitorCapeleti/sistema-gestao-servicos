package br.com.sgs.sistema_gestao_servicos.controller;

import br.com.sgs.sistema_gestao_servicos.model.Cliente;
import br.com.sgs.sistema_gestao_servicos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // --- LEITURA (Read) ---
    @GetMapping
    public List<Cliente> listarTodosClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(cliente -> ResponseEntity.ok(cliente)) // Se encontrar, retorna 200 OK com o cliente
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // --- CRIAÇÃO (Create) ---
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarNovoCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    // --- ATUALIZAÇÃO (Update) ---
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalhes) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteDetalhes);
            return ResponseEntity.ok(clienteAtualizado); // Retorna 200 OK com o cliente atualizado
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Se não encontrou, retorna 404
        }
    }

    // --- DELEÇÃO (Delete) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content, sucesso sem corpo de resposta
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
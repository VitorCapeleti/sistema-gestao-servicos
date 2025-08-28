package br.com.sgs.sistema_gestao_servicos.controller.web;

import br.com.sgs.sistema_gestao_servicos.model.Cliente;
import br.com.sgs.sistema_gestao_servicos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/clientes") // Prefixo para todas as rotas de clientes na web
public class ClienteWebController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("clienteNovo", new Cliente()); // Objeto para o formulário de adição
        return "clientes"; // Retorna o nome do arquivo HTML: templates/clientes.html
    }

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteService.criarNovoCliente(cliente);
        return "redirect:/web/clientes"; // Redireciona para a lista após salvar
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return "redirect:/web/clientes";
    }
}
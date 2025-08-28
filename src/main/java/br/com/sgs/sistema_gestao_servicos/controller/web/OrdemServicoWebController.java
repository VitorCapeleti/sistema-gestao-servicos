package br.com.sgs.sistema_gestao_servicos.controller.web;

import br.com.sgs.sistema_gestao_servicos.dto.OrdemServicoRequestDTO;
import br.com.sgs.sistema_gestao_servicos.service.ClienteService;
import br.com.sgs.sistema_gestao_servicos.service.FuncionarioService;
import br.com.sgs.sistema_gestao_servicos.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/") // Mapeia para a raiz da aplicação
public class OrdemServicoWebController {

    @Autowired private OrdemServicoService osService;
    @Autowired private ClienteService clienteService;
    @Autowired private FuncionarioService funcionarioService;

    @GetMapping
    public String paginaPrincipal(Model model) {
        model.addAttribute("listaOS", osService.listarTodas());
        model.addAttribute("listaClientes", clienteService.listarTodos());
        model.addAttribute("listaFuncionarios", funcionarioService.listarTodos());
        model.addAttribute("osRequest", new OrdemServicoRequestDTO());
        return "index"; // templates/index.html
    }

    @PostMapping("/os/salvar")
    public String salvarOS(@ModelAttribute OrdemServicoRequestDTO osRequest) {
        osService.abrirOS(osRequest);
        return "redirect:/";
    }
    
    @GetMapping("/os/finalizar/{id}")
    public String finalizarOS(@PathVariable Long id) {
        osService.finalizarOS(id);
        return "redirect:/";
    }
}
package br.com.sgs.sistema_gestao_servicos.controller.web;

import br.com.sgs.sistema_gestao_servicos.model.Funcionario;
import br.com.sgs.sistema_gestao_servicos.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/funcionarios")
public class FuncionarioWebController {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        model.addAttribute("funcionarioNovo", new Funcionario());
        return "funcionarios"; // templates/funcionarios.html
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario) {
        funcionarioService.criarNovoFuncionario(funcionario);
        return "redirect:/web/funcionarios";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return "redirect:/web/funcionarios";
    }
}
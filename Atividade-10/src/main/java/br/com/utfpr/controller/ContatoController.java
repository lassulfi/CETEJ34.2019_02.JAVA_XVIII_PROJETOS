package br.com.utfpr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.utfpr.entity.Contato;

@Controller
@RequestMapping("/{tenantId}/contato")
public class ContatoController {

	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Contato contato) {
		return "redirect:/{tenantId}/contato";
	}
}

package com.rafa.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rafa.brewer.model.Cerveja;

@Controller
public class CidadesController
{
	@RequestMapping("/cidades/novo")
	public String novo()
	{
		return "cidade/CadastroCidade";
	}
	
	@RequestMapping(value="/cidades/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			model.addAttribute("mensagem", "Erro no formulário");
		}
		else
		{
			model.addAttribute("mensagem", "Cidade salva com sucesso");
		}
		
		return "cidade/CadastroCidade";
	}
}

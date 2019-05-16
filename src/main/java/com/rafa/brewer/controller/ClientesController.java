package com.rafa.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rafa.brewer.model.Cerveja;

@Controller
public class ClientesController
{
		@RequestMapping("/clientes/novo")
		public String novo()
		{
			return "cliente/CadastroCliente";
		}
		
		@RequestMapping(value="/clientes/novo", method = RequestMethod.POST)
		public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model)
		{
			if (result.hasErrors())
			{
				model.addAttribute("mensagem", "Erro no formulário");
			}
			else
			{
				model.addAttribute("mensagem", "Cliente salvo com sucesso");
			}
			
			return "cliente/CadastroCliente";
		}
		
		
	}



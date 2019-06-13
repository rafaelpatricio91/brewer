package com.rafa.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rafa.brewer.model.Cerveja;
import com.rafa.brewer.model.TipoPessoa;
import com.rafa.brewer.repository.Estados;

@Controller @RequestMapping("/clientes")
public class ClientesController
{
	@Autowired
	private Estados estados;
	
		@RequestMapping("/novo")
		public ModelAndView novo()
		{
			ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
			mv.addObject("tiposPessoa", TipoPessoa.values() );
			mv.addObject("estados", estados.findAll() );
			
			return mv;
		}
		
		@RequestMapping(value="/novo", method = RequestMethod.POST)
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



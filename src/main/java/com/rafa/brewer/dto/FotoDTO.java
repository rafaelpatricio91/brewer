package com.rafa.brewer.dto;

public class FotoDTO
{
	private String nome;
	private String contentType;
	
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getContentType()
	{
		return contentType;
	}
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}
	
	public FotoDTO(String nome, String contentType)
	{
		this.nome = nome;
		this.contentType = contentType;
	}
}

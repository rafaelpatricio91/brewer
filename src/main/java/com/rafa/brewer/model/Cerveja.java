package com.rafa.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.rafa.brewer.validation.SKU;

@Entity
@Table(name="cerveja")
public class Cerveja
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long id;
	@NotBlank(message="SKU é obrigatório") @SKU
	private String sku;
	@NotBlank(message="Nome é obrigatório") @Size(min=3, max=50, message="Nome deve ter mínimo de 3 máximo de 50 caracteres")
	private String nome;
	private String descricao;
	@NotNull(message="O valor é obrigatório")
	@DecimalMin(value="0.99", message="Valor deve ser maior que R$0,99") @DecimalMax(value="1000.00", message="Valor deve ser menor que R$1.000")
	private BigDecimal valor;

	@Column(name="teor_alcoolico")
	@NotNull(message="Teor Alcóolico é obrigatório") @Max(value=100, message="Teor alcóolico não pode ser de 100%")
	private BigDecimal teorAlcoolico; 
	
	@NotNull(message="Comissão é obrigatória") @DecimalMax(value="100", message="Comissão deve ser menor ou igual a 100%")
	private BigDecimal comissao;
	
	@Column(name="quantidade_estoque") @NotNull(message="Quantidade é obrigatória.") @DecimalMin(value="1", message="É preciso pelo menos uma cerveja no estoque")
	private Integer quantidadeEstoque;
	
	@NotNull(message="A origem é obrigatória")
	@Enumerated(EnumType.STRING)
	private Origem origem;
	
	@NotNull(message="O sabor é obrigatório")
	@Enumerated(EnumType.STRING)
	private Sabor sabor;
	
	@ManyToOne @JoinColumn(name="codigo_estilo")
	@NotNull(message="O estilo é obrigatório")
	private Estilo estilo;
	@Column
	private String foto;
	@Column(name="content_type")
	private String contentType;
	
	@PrePersist @PreUpdate
	private void prePersistUpdate()
	{
		sku = sku.toUpperCase();
	}
	
	public String getSku()
	{
		return sku;
	}
	public void setSku(String sku)
	{
		this.sku = sku;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getDescricao()
	{
		return descricao;
	}
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public BigDecimal getValor()
	{
		return valor;
	}
	public void setValor(BigDecimal valor)
	{
		this.valor = valor;
	}
	public BigDecimal getTeorAlcoolico()
	{
		return teorAlcoolico;
	}
	public void setTeorAlcoolico(BigDecimal teorAlcoolico)
	{
		this.teorAlcoolico = teorAlcoolico;
	}
	public BigDecimal getComissao()
	{
		return comissao;
	}
	public void setComissao(BigDecimal comissao)
	{
		this.comissao = comissao;
	}
	public Integer getQuantidadeEstoque()
	{
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque)
	{
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Origem getOrigem()
	{
		return origem;
	}
	public void setOrigem(Origem origem)
	{
		this.origem = origem;
	}
	public Sabor getSabor()
	{
		return sabor;
	}
	public void setSabor(Sabor sabor)
	{
		this.sabor = sabor;
	}
	public Estilo getEstilo()
	{
		return estilo;
	}
	public void setEstilo(Estilo estilo)
	{
		this.estilo = estilo;
	}
	public String getFoto()
	{
		return foto;
	}
	public void setFoto(String foto)
	{
		this.foto = foto;
	}
	public String getContentType()
	{
		return contentType;
	}
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}
	
	public String getFotoOuMock()
	{
		return !StringUtils.isEmpty(foto) ? foto : "cerveja-mock.png";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}



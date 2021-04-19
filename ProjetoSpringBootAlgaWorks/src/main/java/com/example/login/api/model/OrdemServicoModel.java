package com.example.login.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import com.example.login.domain.model.StatusOrdemServicos;

public class OrdemServicoModel {

	private Long id;
	private ClienteResumoModel cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServicos status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public ClienteResumoModel getCliente() {
		return cliente;
	}
	public void setCliente(ClienteResumoModel cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOrdemServicos getStatus() {
		return status;
	}
	public void setStatus(StatusOrdemServicos status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	
	
}

package com.example.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.domain.exception.NegocioException;
import com.example.login.domain.model.Cliente;
import com.example.login.domain.repository.ClienteRepository;


@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	public Cliente salvar(Cliente cliente) {	
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Ja existe cliente cadastrado com este email");			
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {		
		clienteRepository.deleteById(clienteId);
	}
	
}

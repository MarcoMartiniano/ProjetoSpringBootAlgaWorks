package com.example.login.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ctc.wstx.shaded.msv_core.driver.textui.Debug;
import com.example.login.api.model.OrdemServicoInput;
import com.example.login.api.model.OrdemServicoModel;
import com.example.login.domain.model.OrdemServico;
import com.example.login.domain.repository.OrdemServicoRepository;
import com.example.login.domain.service.GestaoOrdemServicoService;


@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	
	//precisa transformar ModelMapper em uma instacia gerenciavel do spring
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		return toModel(gestaoOrdemServicoService.criar(ordemServico));		
	}
	@GetMapping
	public List<OrdemServicoModel> listar(){
		return toColletionModel(ordemServicoRepository.findAll());		
	}
		
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		
		if(ordemServico.isPresent()) {
			OrdemServicoModel ordemServicoModel =  toModel(ordemServico.get());								
			return ResponseEntity.ok(ordemServicoModel);			
		}
				
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar (@PathVariable Long ordemServicoId) {
		gestaoOrdemServicoService.finalizar(ordemServicoId);	
		
	}
	
	
	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico,OrdemServicoModel.class);
				
	}
	
	private List<OrdemServicoModel> toColletionModel(List<OrdemServico>ordensServico){				
		return ordensServico.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());					
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
		
	}
	
}

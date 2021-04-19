package com.example.login.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.api.model.Comentario;
import com.example.login.api.model.ComentarioInput;
import com.example.login.api.model.ComentarioModel;
import com.example.login.domain.exception.EntidadeNaoEncontradaException;
import com.example.login.domain.model.OrdemServico;
import com.example.login.domain.repository.OrdemServicoRepository;
import com.example.login.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService  gestaoOrdemServicoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@GetMapping
	public List<ComentarioModel>listar(@PathVariable Long ordemServicoId){
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		return toCollectionModel(ordemServico.getComentarios());
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar (@PathVariable Long ordemServicoId, 
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServicoService.adiconarComentario(ordemServicoId, comentarioInput.getDescricao()); 
		return toModel(comentario);					
	}
	
	private ComentarioModel toModel (Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
				
	}
	
	private List<ComentarioModel> toCollectionModel (List<Comentario> comentarios){
		return comentarios.stream()
				.map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
			
	}
}

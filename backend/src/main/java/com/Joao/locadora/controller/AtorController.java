package com.Joao.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Joao.locadora.DTO.RequestDTO.AtorDTO;
import com.Joao.locadora.DTO.ResponseDTO.AtorResponseDTO;
import com.Joao.locadora.model.ResponseModel;
import com.Joao.locadora.service.AtorService;

@RestController
@RequestMapping("/ator")
public class AtorController {
    
    @Autowired
    private AtorService atorService;

    @PostMapping("/novo")
    public ResponseModel<AtorResponseDTO> salvar(@RequestBody AtorDTO request){
        return atorService.salvarAtor(request);
    }

    @GetMapping("/{id}")
    public ResponseModel<AtorResponseDTO> buscarPorId(@PathVariable int id){
        return atorService.buscarAtor(id);
    }

    @GetMapping("/listar")
    public ResponseModel<List<AtorResponseDTO>> buscarTodos(){
        return atorService.buscarTodos();
    }

    @PutMapping("/{id}")
    public ResponseModel<AtorResponseDTO> atualizar(@PathVariable int id, @RequestBody AtorDTO request){
        return atorService.atualizarAtor(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseModel<String> deletar(@PathVariable int id){
        return atorService.deletarAtor(id);
    }
}

package com.Joao.locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Joao.locadora.DTO.RequestDTO.DiretorDTO;
import com.Joao.locadora.DTO.ResponseDTO.DiretorResponseDTO;
import com.Joao.locadora.model.ResponseModel;
import com.Joao.locadora.service.DiretorService;

import java.util.List;

@RestController
@RequestMapping("/diretor")
public class DiretorController {
    @Autowired
    private DiretorService diretorService;

    @PostMapping("/novo")
    public ResponseModel<DiretorResponseDTO> salvar(@RequestBody DiretorDTO request){
        return diretorService.salvarDiretor(request);
    }

    @GetMapping("/{id}")
    public ResponseModel<DiretorResponseDTO> buscarPorId(@PathVariable int id){
        return diretorService.buscarDiretor(id);
    }

    @GetMapping("/listar")
    public ResponseModel<List<DiretorResponseDTO>> buscarTodos(){
        return diretorService.buscarTodos();
    }

    @PutMapping("/{id}")
    public ResponseModel<DiretorResponseDTO> atualizar(@PathVariable int id, @RequestBody DiretorDTO request){
        return diretorService.atualizarDiretor(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseModel<String> deletar(@PathVariable int id){
        return diretorService.deletarDiretor(id);
    }
}

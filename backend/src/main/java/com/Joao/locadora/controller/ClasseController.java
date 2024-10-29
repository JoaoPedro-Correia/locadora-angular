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

import com.Joao.locadora.DTO.RequestDTO.ClasseDTO;
import com.Joao.locadora.DTO.ResponseDTO.ClasseResponseDTO;
import com.Joao.locadora.model.ResponseModel;
import com.Joao.locadora.service.ClasseService;

@RestController
@RequestMapping("/classe")
public class ClasseController {
      @Autowired
     private ClasseService classeService;

     @PostMapping("/novo")
     public ResponseModel<ClasseResponseDTO> salvar(@RequestBody ClasseDTO request){
         return classeService.salvarClasse(request);
     }

     @GetMapping("/{id}")
     public ResponseModel<ClasseResponseDTO> buscarPorId(@PathVariable int id){
         return classeService.buscarClasse(id);
     }

     @GetMapping("/listar")
     public ResponseModel<List<ClasseResponseDTO>> buscarTodos(){
         return classeService.buscarTodos();
     }

     @PutMapping("/{id}")
     public ResponseModel<ClasseResponseDTO> atualizar(@PathVariable int id, @RequestBody ClasseDTO request){
         return classeService.atualizarClasse(id, request);
     }

     @DeleteMapping("/{id}")
     public ResponseModel<String> deletar(@PathVariable int id){
         return classeService.deletarClasse(id);
     }
}

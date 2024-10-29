package com.Joao.locadora.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Joao.locadora.DTO.RequestDTO.ClasseDTO;
import com.Joao.locadora.DTO.ResponseDTO.ClasseResponseDTO;
import com.Joao.locadora.mapper.ClasseMap;
import com.Joao.locadora.model.Classe;
import com.Joao.locadora.model.ResponseModel;
import com.Joao.locadora.repository.ClasseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseService {
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private ClasseMap classeMapper;

    public ResponseModel<ClasseResponseDTO> salvarClasse(ClasseDTO obj) {
        ResponseModel<ClasseResponseDTO> response = new ResponseModel<>();

        if(obj.nome() == null || obj.prazoDevolucao() == null || obj.valor() == null){
            response.setMensagem("Preencha todos os campos");
            response.setStatus(false);
            return response;
        }

        try {
            response.setDados(classeMapper.toDTO(classeRepository.save(classeMapper.toEntity(obj))));
            response.setMensagem("Registro salvo com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao salvar registro");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<ClasseResponseDTO> buscarClasse(int id){
        ResponseModel<ClasseResponseDTO> response = new ResponseModel<>();
        try {
            response.setDados(classeMapper.toDTO(classeRepository.findById(id).orElseThrow()));
            response.setMensagem("Registro encontrado com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao buscar registro");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<List<ClasseResponseDTO>> buscarTodos(){
        ResponseModel<List<ClasseResponseDTO>> response = new ResponseModel<>();
        try {
            response.setDados(classeRepository.findAll().stream().map(classeMapper::toDTO).collect(Collectors.toList()));
            response.setMensagem("Registros encontrados com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao buscar registros");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<ClasseResponseDTO> atualizarClasse(int id, ClasseDTO request){
        ResponseModel<ClasseResponseDTO> response = new ResponseModel<>();

        if(request.nome() == null || request.prazoDevolucao() == null || request.valor() == null){
            response.setMensagem("Preencha todos os campos");
            response.setStatus(false);
            return response;
        }
        
        try {
            Classe obj = classeRepository.findById(id).orElseThrow();
            obj.setNome(request.nome());
            obj.setValor(request.valor());
            obj.setPrazoDevolucao(request.prazoDevolucao());

            response.setDados(classeMapper.toDTO(classeRepository.save(obj)));
            response.setMensagem("Registro atualizado com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao atualizar registro");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<String> deletarClasse(int id){
        ResponseModel<String> response = new ResponseModel<>();
        try {
            classeRepository.deleteById(id);
            response.setMensagem("Registro deletado com sucesso");
        } catch (ConstraintViolationException e){
            response.setMensagem("Erro ao deletar registro. Ele está vínculado a outros registros");
            response.setStatus(false);
        }
        return response;
    }
}

package com.Joao.locadora.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Joao.locadora.DTO.RequestDTO.DiretorDTO;
import com.Joao.locadora.DTO.ResponseDTO.DiretorResponseDTO;
import com.Joao.locadora.mapper.DiretorMap;
import com.Joao.locadora.model.Diretor;
import com.Joao.locadora.model.ResponseModel;
import com.Joao.locadora.repository.DiretorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiretorService {
    @Autowired
    private DiretorRepository diretorRepository;
    @Autowired
    private DiretorMap diretorMapper;

    public ResponseModel<DiretorResponseDTO> salvarDiretor(DiretorDTO obj) {
        ResponseModel<DiretorResponseDTO> response = new ResponseModel<>();

        if(obj.nome() == null){
            response.setMensagem("Preencha todos os campos");
            response.setStatus(false);
            return response;
        }

        try {
            response.setDados(diretorMapper.toDTO(diretorRepository.save(diretorMapper.toEntity(obj))));
            response.setMensagem("Registro salvo com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao salvar registro");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<DiretorResponseDTO> buscarDiretor(int id){
        ResponseModel<DiretorResponseDTO> response = new ResponseModel<>();
        try {
            response.setDados(diretorMapper.toDTO(diretorRepository.findById(id).orElseThrow()));
            response.setMensagem("Registro encontrado com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao buscar registro");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<List<DiretorResponseDTO>> buscarTodos(){
        ResponseModel<List<DiretorResponseDTO>> response = new ResponseModel<>();
        try {
            response.setDados(diretorRepository.findAll().stream().map(diretorMapper::toDTO).collect(Collectors.toList()));
            response.setMensagem("Registros encontrados com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao buscar registros");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<DiretorResponseDTO> atualizarDiretor(int id, DiretorDTO request){
        ResponseModel<DiretorResponseDTO> response = new ResponseModel<>();

        if(request.nome() == null){
            response.setMensagem("Preencha todos os campos");
            response.setStatus(false);
            return response;
        }

        try {
            Diretor obj = diretorRepository.findById(id).orElseThrow();
            obj.setNome(request.nome());

            response.setDados(diretorMapper.toDTO(diretorRepository.save(obj)));
            response.setMensagem("Registro atualizado com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao atualizar registro");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<String> deletarDiretor(int id){
        ResponseModel<String> response = new ResponseModel<>();
        try {
            diretorRepository.deleteById(id);
            response.setMensagem("Registro deletado com sucesso");
        } catch (ConstraintViolationException e){
            response.setMensagem("Não foi possível apagar o registro, pois, está vinculado a títulos");
            response.setStatus(false);
        }
        return response;
    }
}

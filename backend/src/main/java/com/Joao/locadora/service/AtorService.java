package com.Joao.locadora.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Joao.locadora.DTO.RequestDTO.AtorDTO;
import com.Joao.locadora.DTO.ResponseDTO.AtorResponseDTO;
import com.Joao.locadora.mapper.AtorMap;
import com.Joao.locadora.model.Ator;
import com.Joao.locadora.model.ResponseModel;
import com.Joao.locadora.repository.AtorRepository;

@Service
public class AtorService{
    @Autowired
    private AtorRepository atorRepository;
    @Autowired
    private AtorMap atorMapper;

    public ResponseModel<AtorResponseDTO> salvarAtor(AtorDTO ator) {
        ResponseModel<AtorResponseDTO> response = new ResponseModel<>();
        
        if(ator.nome() == null){
            response.setMensagem("Preencha todos os campos");
            response.setStatus(false);
            return response;
        }

        try {
            response.setDados(atorMapper.toDTO(atorRepository.save(atorMapper.toEntity(ator))));
            response.setMensagem("Ator salvo com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao salvar ator");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<AtorResponseDTO> buscarAtor(int id){
        ResponseModel<AtorResponseDTO> response = new ResponseModel<>();
        try {
            response.setDados(atorMapper.toDTO(atorRepository.findById(id).orElseThrow()));
            response.setMensagem("Ator encontrado com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao buscar ator");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<List<AtorResponseDTO>> buscarTodos(){
        ResponseModel<List<AtorResponseDTO>> response = new ResponseModel<>();
        try {
            response.setDados(atorRepository.findAll().stream().map(atorMapper::toDTO).collect(Collectors.toList()));
            response.setMensagem("Atores encontrados com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao buscar atores");
            response.setStatus(false);
        }
        return response;
    }       

    public ResponseModel<AtorResponseDTO> atualizarAtor(int id, AtorDTO request){
        ResponseModel<AtorResponseDTO> response = new ResponseModel<>();

        if(request.nome() == null){
            response.setMensagem("Preencha todos os campos");
            response.setStatus(false);
            return response;
        }

        try {
            Ator ator = atorRepository.findById(id).orElseThrow();
            ator.setNome(request.nome());
            response.setDados(atorMapper.toDTO(atorRepository.save(ator)));
            response.setMensagem("Ator atualizado com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao atualizar ator");
            response.setStatus(false);
        }
        return response;
    }

    public ResponseModel<String> deletarAtor(int id){
        ResponseModel<String> response = new ResponseModel<>();
        try {
            atorRepository.deleteById(id);
            response.setMensagem("Ator deletado com sucesso");
        } catch (Exception e) {
            response.setMensagem("Erro ao deletar ator");
            response.setStatus(false);
        }
        return response;
    }

}

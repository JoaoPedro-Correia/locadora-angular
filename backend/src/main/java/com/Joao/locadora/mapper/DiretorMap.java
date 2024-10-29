package com.Joao.locadora.mapper;

import org.mapstruct.Mapper;

import com.Joao.locadora.DTO.RequestDTO.DiretorDTO;
import com.Joao.locadora.DTO.ResponseDTO.DiretorResponseDTO;
import com.Joao.locadora.model.Diretor;

@Mapper(componentModel = "spring")
public interface DiretorMap {
    DiretorResponseDTO toDTO(Diretor obj);
    Diretor toEntity(DiretorDTO requestDTO);
}

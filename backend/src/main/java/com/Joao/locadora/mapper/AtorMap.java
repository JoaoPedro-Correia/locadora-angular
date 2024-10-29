package com.Joao.locadora.mapper;

import org.mapstruct.Mapper;

import com.Joao.locadora.DTO.RequestDTO.AtorDTO;
import com.Joao.locadora.DTO.ResponseDTO.AtorResponseDTO;
import com.Joao.locadora.model.Ator;

@Mapper(componentModel = "spring")
public interface AtorMap {
    
    AtorResponseDTO toDTO(Ator ator);
    Ator toEntity(AtorDTO atorRequestDTO);
}

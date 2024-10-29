package com.Joao.locadora.mapper;

import org.mapstruct.Mapper;

import com.Joao.locadora.DTO.RequestDTO.ClasseDTO;
import com.Joao.locadora.DTO.ResponseDTO.ClasseResponseDTO;
import com.Joao.locadora.model.Classe;

@Mapper(componentModel = "spring")
public interface ClasseMap {
    ClasseResponseDTO toDTO(Classe obj);
    Classe toEntity(ClasseDTO requestDTO);
}

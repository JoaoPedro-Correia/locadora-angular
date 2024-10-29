package com.Joao.locadora.DTO.ResponseDTO;

import java.util.Date;

public record ClasseResponseDTO(
    int id,
    String nome,
    double valor,
    Date prazoDevolucao
) {
    
}

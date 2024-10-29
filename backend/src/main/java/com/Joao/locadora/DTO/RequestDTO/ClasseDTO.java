package com.Joao.locadora.DTO.RequestDTO;

import java.util.Date;

public record ClasseDTO(
    String nome,
    Double valor,
    Date prazoDevolucao
) {
    
}

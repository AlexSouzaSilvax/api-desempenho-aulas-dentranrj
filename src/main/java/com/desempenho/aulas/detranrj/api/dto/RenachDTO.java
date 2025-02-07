package com.desempenho.aulas.detranrj.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenachDTO {

    @NotBlank(message = "RENACH não pode ser vazio.")
    @Pattern(regexp = "\\d{9}", message = "RENACH deve conter exatamente 9 dígitos numéricos.")
    private String renach;

}

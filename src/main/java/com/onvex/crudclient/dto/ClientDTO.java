package com.onvex.crudclient.dto;

import com.onvex.crudclient.entities.Client;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO(){

    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }
    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    @NotBlank(message = "Campo obrigatório não preenchido: Nome do cliente.")
    public String getName() {
        return name;
    }

    @NotNull(message = "Campo obrigatório não preenchido: CPF do cliente.")
    @Column(unique = true)
    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    @NotNull(message = "Campo obrigatório não preenchido: Data de nascimento do cliente.")
    @PastOrPresent(message = "A data de nascimento não pode ser uma data futura.")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}

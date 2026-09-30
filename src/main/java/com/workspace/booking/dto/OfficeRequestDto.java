package com.workspace.booking.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull


public class OfficeRequestDto {

    @NotBlank(message = "Наименование офиса не должно быть пустым")
    private String name;

    @NotBlank(message = "Адресс нахождения офиса")
    private String address;

    @NotBlank(message = "Город нахождения офиса необходим")
    private String city;

}

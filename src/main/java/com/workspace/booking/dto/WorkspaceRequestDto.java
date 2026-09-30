package com.workspace.booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class WorkspaceRequestDto {

    @NotBlank(message = "Номер стола не должен быть пуст")
    private String code;
    @NotBlank(message = "Наименование рабочего места")
    private String name;
    private String zone;
    @NotNull(message = "Место обезательна привязно к городскому оффису")
    @Min(value = 1, message = "Вместимость должна быть не менее 1")
    private int capacity;
    @NotNull(message = "Номер оффисса")
    private Long officeId;



}

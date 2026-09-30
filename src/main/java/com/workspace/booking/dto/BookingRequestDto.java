package com.workspace.booking.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class BookingRequestDto {
    @NotNull(message = "ID рабочего места обязательно")
    private Long workspaceId;
    @NotNull(message = "ID пользователя обязательно")
    private Long userId;
    @NotNull(message = "Время начала бронирования обязательно")
    @FutureOrPresent(message = "Время начала должно быть в будущем")
    private LocalDateTime startTime;
    @NotNull(message = "Время окончания бронирования обязательно")
    @Future(message = "Время окончания должно быть в будущем")
    private LocalDateTime endTime;
}

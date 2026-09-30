package com.workspace.booking.service;


import com.workspace.booking.dto.BookingRequestDto;
import com.workspace.booking.entity.Booking;
import com.workspace.booking.entity.User;
import com.workspace.booking.entity.Workspace;
import com.workspace.booking.repository.BookingRepository;
import com.workspace.booking.repository.UserRepository;
import com.workspace.booking.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    public Booking createBooking(BookingRequestDto requestDto){
        if (!requestDto.getEndTime().isAfter(requestDto.getStartTime())) {
            throw new IllegalArgumentException("Время окончания должно быть позже времени начала");
        }

        Workspace workspace = workspaceRepository.findById(requestDto.getWorkspaceId())
                .orElseThrow(() -> new IllegalArgumentException("Рабочее место с id " + requestDto.getWorkspaceId() + " не найдено"));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с id " + requestDto.getUserId() + " не найден"));

        boolean hasOverlap = bookingRepository.existsOverLappingBooking(
                requestDto.getWorkspaceId(),
                requestDto.getStartTime(),
                requestDto.getEndTime()
        );
        if (hasOverlap){
            throw new IllegalStateException("Рабочее место уже забронировано на это время!");
        }

        Booking booking = new Booking();
        booking.setWorkspace(workspace);
        booking.setUser(user);
        booking.setStartTime(requestDto.getStartTime());
        booking.setEndTime(requestDto.getEndTime());
        booking.setStatus(Booking.Status.CONFIRMED);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Бронирование с id " + bookingId + " не найдено"));

        booking.setStatus(Booking.Status.CANCELLED);
        return bookingRepository.save(booking);
    }

}

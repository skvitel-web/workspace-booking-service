package com.workspace.booking.repository;

import com.workspace.booking.entity.Booking;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT COUNT(b) > 0 FROM Booking b
        WHERE b.workspace.id = :workspaceId
          AND b.status = com.workspace.booking.entity.Booking.Status.CONFIRMED
          AND b.startTime < :endTime
          AND b.endTime > :startTime
    """)
    boolean existsOverLappingBooking (
            @Param("workspaceId") Long workspaceId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
            );

    List<Booking> findByUserId(Long userId);
}

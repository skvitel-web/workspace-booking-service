package com.workspace.booking.repository;


import com.workspace.booking.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

    boolean existsByName(String name);
    Optional<Office> findByName(String name);


}

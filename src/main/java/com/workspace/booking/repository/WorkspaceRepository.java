package com.workspace.booking.repository;

import com.workspace.booking.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface  WorkspaceRepository extends JpaRepository<Workspace, Long> {

        Optional<Workspace> findByCode(String code);
        List<Workspace> findByActiveTrue();

        boolean existsByCode(String code);
}

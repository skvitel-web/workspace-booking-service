package com.workspace.booking.controller;


import com.workspace.booking.dto.WorkspaceRequestDto;
import com.workspace.booking.entity.Workspace;
import com.workspace.booking.service.WorkspaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@Valid @RequestBody WorkspaceRequestDto requestDto){

        Workspace response = workspaceService.createWorkspace(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspace(){
        List<Workspace> workspaces = workspaceService.getAllWokSpaces();

        return ResponseEntity.ok(workspaces);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable Long id){
        Workspace workspace = workspaceService.getWorkspaceById(id);

        return ResponseEntity.ok(workspace);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable Long id, @Valid @RequestBody WorkspaceRequestDto requestDto){

        Workspace updateWorkspace = workspaceService.updateWorkspace(id, requestDto);

        return ResponseEntity.ok(updateWorkspace);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long id){

        workspaceService.deleteWorkspace(id);

        return ResponseEntity.noContent().build();
    }
}

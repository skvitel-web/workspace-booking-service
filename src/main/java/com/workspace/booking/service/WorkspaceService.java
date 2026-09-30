package com.workspace.booking.service;

import com.workspace.booking.dto.WorkspaceRequestDto;
import com.workspace.booking.entity.Office;
import com.workspace.booking.entity.Workspace;
import com.workspace.booking.repository.OfficeRepository;
import com.workspace.booking.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final OfficeRepository officeRepository;

        public Workspace createWorkspace (WorkspaceRequestDto requestDto){
            if (workspaceRepository.existsByCode(requestDto.getCode())) {
                throw new IllegalStateException("Такое рабочие место уже занято!");
            }

            Office office = officeRepository.findById(requestDto.getOfficeId())
                    .orElseThrow(() -> new IllegalArgumentException("Офис с таким ID не найден"));

            Workspace workspace = new Workspace();
            workspace.setCode(requestDto.getCode());
            workspace.setName(requestDto.getName());
            workspace.setCapacity(requestDto.getCapacity());
            workspace.setZone(requestDto.getZone());
            workspace.setOffice(office);
            return workspaceRepository.save(workspace);
        }

        public List<Workspace> getAllWokSpaces(){
            return workspaceRepository.findAll();
        }

        public Workspace getWorkspaceById(Long id){
            return workspaceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Рабочее место с id " + id + " не найдено"));
        }

        public Workspace updateWorkspace(Long id, WorkspaceRequestDto requestDto){

            Workspace workspace = getWorkspaceById(id);

            if (!workspace.getOffice().getId().equals(requestDto.getOfficeId())) {
                Office newOffice = officeRepository.findById(requestDto.getOfficeId())
                        .orElseThrow(() -> new IllegalArgumentException("Новый офис с id " + requestDto.getOfficeId() + " не найден"));
                workspace.setOffice(newOffice);
            }

            workspace.setCode(requestDto.getCode());
            workspace.setName(requestDto.getName());
            workspace.setZone(requestDto.getZone());
            workspace.setCapacity(requestDto.getCapacity());


            return workspaceRepository.save(workspace);
        }

        public void deleteWorkspace(Long id){
            Workspace workspace = getWorkspaceById(id);
            workspaceRepository.delete(workspace);
        }
    }

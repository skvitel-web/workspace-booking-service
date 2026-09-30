package com.workspace.booking.controller;

import com.workspace.booking.dto.OfficeRequestDto;
import com.workspace.booking.entity.Office;
import com.workspace.booking.service.OfficeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeService officeService;

    @PostMapping
    public ResponseEntity<Office> createOffice(@Valid @RequestBody OfficeRequestDto requestDto){

        Office response = officeService.createOffice(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Office>> getAllOffices() {
        List<Office> offices = officeService.getAllOffice();
        return ResponseEntity.ok(offices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> getOfficeByID(@PathVariable Long id){

        Office office = officeService.getOfficeById(id);


        return ResponseEntity.ok(office);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> updateOffice(@PathVariable Long id, @Valid @RequestBody OfficeRequestDto requestDto){

        Office updateOffice = officeService.updateOffice(id, requestDto);

        return ResponseEntity.ok(updateOffice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id){
        officeService.deleteOffice(id);

        return ResponseEntity.noContent().build();
    }


}

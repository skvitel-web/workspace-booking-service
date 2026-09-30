package com.workspace.booking.service;

import com.workspace.booking.dto.OfficeRequestDto;
import com.workspace.booking.entity.Office;
import com.workspace.booking.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class OfficeService {

    private final OfficeRepository officeRepository;

    public Office createOffice(OfficeRequestDto requestDto){

        if (officeRepository.existsByName(requestDto.getName())){
            throw new IllegalStateException("Офис с таким названием уже имеется");
        }

        Office office = new Office();
        office.setName(requestDto.getName());
        office.setAddress(requestDto.getAddress());



        return  officeRepository.save(office);
    }

    public List<Office> getAllOffice() {
        return officeRepository.findAll();
    }

    public Office getOfficeById(Long id) {
        return officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found with id: " + id));
    }

    public Office updateOffice(Long id, OfficeRequestDto requestDto){
        Office office = getOfficeById(id);
        office.setName(requestDto.getName());
        office.setAddress(requestDto.getAddress());

        return officeRepository.save(office);
    }

    public void deleteOffice(Long id){

        Office office = getOfficeById(id);
        officeRepository.delete(office);

    }

}

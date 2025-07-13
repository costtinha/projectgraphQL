package com.graphql.project.service.officeService;

import com.graphql.project.dtos.CreateOffice;
import com.graphql.project.entity.Office;
import com.graphql.project.persistance.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {
    private final OfficeRepository repository;
    private final OfficeMapper mapper;

    public OfficeService(OfficeRepository repository, OfficeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Office> Offices(){
        return repository.findAllOffice();
    }

    public Office findOfficeById(int id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office with id "+ id + "Not found"));

    }

    public Office createOffice(CreateOffice dto) {
        Office office = mapper.officeDtoToOffice(dto);
        repository.save(office);
        return office;
    }

    public Office deleteOffice(int id) {
        Office office = findOfficeById(id);
        repository.deleteById(id);
        return office;
    }

    public Office updateOffice(int code, CreateOffice dto) {
        Office office = findOfficeById(code);
        office.setAddress1(dto.address1());
        office.setCountry(dto.country());
        office.setCity(dto.city());
        office.setPhone(dto.phone());
        office.setState(dto.state());
        office.setPostalCode(dto.postalCode());
        office.setTerritory(dto.territory());
        repository.save(office);
        return office;
    }
}

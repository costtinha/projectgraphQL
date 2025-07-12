package com.graphql.project.service.officeService;

import com.graphql.project.dtos.CreateOffice;
import com.graphql.project.entity.Office;

import java.util.Collections;

public class OfficeMapper {
    public Office officeDtoToOffice(CreateOffice dto){
        Office office = new Office();
        office.setAddress1(dto.address1());
        office.setCountry(dto.country());
        office.setCity(dto.city());
        office.setPhone(dto.phone());
        office.setState(dto.state());
        office.setPostalCode(dto.postalCode());
        office.setTerritory(dto.territory());
        office.setEmployees(Collections.emptyList());
        return office;
    }

}

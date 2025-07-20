package com.graphql.project.controller;

import com.graphql.project.dtos.CreateOffice;
import com.graphql.project.entity.Office;
import com.graphql.project.service.officeService.OfficeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OfficeController {
    private final OfficeService service;

    public OfficeController(OfficeService service){
        this.service = service;
    }

    @QueryMapping
    public List<Office> offices(){
        return service.Offices();
    }

    @QueryMapping
    public Office officesById(@Argument("code")int code){
        return service.findOfficeById(code);
    }

    @MutationMapping
    public Office createOffice(@Argument("input")CreateOffice dto){
        return service.createOffice(dto);
    }

    @MutationMapping
    public Office deleteOffice(@Argument("code")int id){
        return service.deleteOffice(id);
    }

    @MutationMapping
    public Office updateOffice(@Argument("code") int code, @Argument("input") CreateOffice dto){
        return service.updateOffice(code,dto);
    }


}

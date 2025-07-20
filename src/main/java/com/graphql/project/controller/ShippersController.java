package com.graphql.project.controller;

import com.graphql.project.dtos.CreateShipper;
import com.graphql.project.entity.Shippers;
import com.graphql.project.service.shippersService.ShippersService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ShippersController {
    private final ShippersService service;

    public ShippersController(ShippersService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Shippers> shippers(){
        return service.findShippers();
    }

    @QueryMapping
    public Shippers shipperById(@Argument("shipId") int id){
        return service.findShipperById(id);
    }

    @MutationMapping
    public  Shippers createShippers(@Argument("input")CreateShipper dto){
        return service.createShippers(dto);
    }

    @MutationMapping
    public Shippers deleteShippers(@Argument("shipId") int id){
        return service.deleteShippersById(id);
    }

    @MutationMapping
    public Shippers updateShippers(@Argument("shipId")int id, @Argument("input") CreateShipper dto){
        return service.updateShippers(id,dto);
    }
}

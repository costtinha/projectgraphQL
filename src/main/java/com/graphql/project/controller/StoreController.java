package com.graphql.project.controller;

import com.graphql.project.dtos.CreateStore;
import com.graphql.project.entity.Store;
import com.graphql.project.service.storeService.StoreService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StoreController {
    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Store> stores(){
        return service.findStores();
    }

    @QueryMapping
    public Store storeById(@Argument("storeId")int id){
        return service.findStoreById(id);
    }

    @MutationMapping
    public Store createStore(@Argument("input")CreateStore dto){
        return service.createStore(dto);
    }

    @MutationMapping
    public Store deleteStore(@Argument("storeId")int id){
        return service.deleteStoreById(id);
    }

    @MutationMapping
    public Store updateStore(@Argument("storeId")int id, @Argument("input") CreateStore dto){
        return service.updateStore(id,dto);
    }
}

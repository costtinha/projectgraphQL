package com.graphql.project.service.storeService;

import com.graphql.project.dtos.CreateStore;
import com.graphql.project.entity.Store;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class StoreMapper {
    public Store storeDtoToStore(CreateStore dto){
        Store store = new Store();
        store.setStoreName(dto.storeName());
        store.setStoreOrders(Collections.emptyList());
        return store;
    }
}

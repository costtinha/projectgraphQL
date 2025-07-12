package com.graphql.project.service.storeService;

import com.graphql.project.dtos.CreateStore;
import com.graphql.project.entity.Store;
import com.graphql.project.persistance.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    private final StoreRepository repository;
    private final StoreMapper mapper;

    public StoreService(StoreRepository repository, StoreMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Store> findStores() {
        return repository.findAll();
    }

    public Store findStoreById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store with id " + id + " not found"));
    }

    public Store createStore(CreateStore dto) {
        return repository.save(mapper.storeDtoToStore(dto));
    }

    public Store deleteStoreById(int id) {
        Store store = findStoreById(id);
        repository.delete(store);
        return store;
    }

    public Store updateStore(int id, CreateStore dto) {
        Store store = findStoreById(id);
        store = mapper.storeDtoToStore(dto);
        return repository.save(store);
    }
}

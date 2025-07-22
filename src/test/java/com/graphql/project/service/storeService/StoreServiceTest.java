package com.graphql.project.service.storeService;

import com.graphql.project.dtos.CreateStore;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Store;
import com.graphql.project.persistance.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StoreServiceTest {

    @Mock
    private StoreRepository repository;

    @Mock
    private StoreMapper mapper;

    @InjectMocks
    private StoreService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testFindStores(){
        List<Store> mockedList = List.of(new Store(),new Store());
        when(repository.findStoreAll()).thenReturn(mockedList);

        List<Store> stores = service.findStores();

        assertEquals(2,stores.size());

        verify(repository).findStoreAll();
    }

    @Test
    void testFindStoreById_Success(){
        Store store = new Store();
        store.setStoreId(1);
        store.setStoreName("Loja");
        when(repository.findById(1)).thenReturn(Optional.of(store));

        Store found = service.findStoreById(1);

        assertNotNull(found);
        assertEquals("Loja",found.getStoreName());
        assertEquals(1,found.getStoreId());

        verify(repository).findById(1);
    }

    @Test
    void  testFindStoreById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,() -> service.findStoreById(99));

        assertEquals("Store with id 99 not found",exception.getMessage());

        verify(repository).findById(99);
    }

    @Test
    void testCreateStore(){
        CreateStore storeDto = new CreateStore(
                "Downtown Hobby Shop"
        );
        Store store = new Store();
        store.setStoreName("Downtown Hobby Shop");
        store.setStoreId(1);
        when(mapper.storeDtoToStore(storeDto)).thenReturn(store);
        when(repository.save(store)).thenReturn(store);

        Store savedStore = service.createStore(storeDto);

        assertEquals("Downtown Hobby Shop", savedStore.getStoreName());
        assertEquals(1,savedStore.getStoreId());

        verify(repository).save(store);
        verify(mapper).storeDtoToStore(storeDto);
    }

    @Test
    void testDeleteStoreById(){
        Store store = new Store();
        store.setStoreId(1);
        store.setStoreName("Nome");
        when(repository.findById(1)).thenReturn(Optional.of(store));

        Store deleted = service.deleteStoreById(1);

        assertNotNull(deleted);
        assertEquals(1,deleted.getStoreId());
        assertEquals("Nome",deleted.getStoreName());

        verify(repository).findById(1);
        verify(repository).delete(store);
    }

    @Test
    void testUpdateStore(){
        CreateStore storeDto = new CreateStore(
                "Downtown Hobby Shop"
        );
        Store store = new Store();
        store.setStoreId(1);
        when(repository.findById(1)).thenReturn(Optional.of(store));
        when(repository.save(any())).thenReturn(store);

        Store updated = service.updateStore(1,storeDto);

        assertEquals(1,updated.getStoreId());
        assertEquals("Downtown Hobby Shop",updated.getStoreName());

        verify(repository).findById(1);
        verify(repository).save(any());
    }
}

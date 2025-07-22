package com.graphql.project.service.officeService;


import com.graphql.project.dtos.CreateOffice;
import com.graphql.project.entity.Office;
import com.graphql.project.persistance.OfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class OfficeServiceTest {
    @Mock
    private OfficeRepository repository;

    @Mock
    private OfficeMapper mapper;

    @InjectMocks
    private OfficeService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOffice(){
        CreateOffice dto = new CreateOffice("São Paulo","123456",
                "Av. Paulista",
                "SP","Brasil",
                123456,"Suldeste");

        Office mappedOffice = new Office();
        mappedOffice.setState("SP");
        when(mapper.officeDtoToOffice(dto)).thenReturn(mappedOffice);
        when(repository.save(mappedOffice)).thenReturn(mappedOffice);

        Office result = service.createOffice(dto);


        assertNotNull(result);

        assertEquals("SP",result.getState());
        verify(mapper).officeDtoToOffice(dto);
        verify(repository).save(mappedOffice);

    }

    @Test
    void testFindOfficeById_Success(){
        Office office = new Office();
        office.setCode(1);
        office.setCity("Niteroi");

        when(repository.findById(1)).thenReturn(Optional.of(office));

        Office result = service.findOfficeById(1);
        assertNotNull(result);
        assertEquals("Niteroi",result.getCity());
        verify(repository).findById(1);
    }

    @Test
    void testFindOfficeById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.findOfficeById(99));
        assertEquals("Office with id 99 Not found",ex.getMessage());
    }

    @Test
    void testDeleteOfficeById(){
        Office office = new Office();
        office.setCode(1);
        when(repository.findById(1)).thenReturn(Optional.of(office));
        Office deleted = service.deleteOffice(1);

        assertNotNull(deleted);
        assertEquals(1,deleted.getCode());
        verify(repository).deleteById(1);

    }

    @Test
    void testUpdateOffice(){
        CreateOffice dto = new CreateOffice("São Paulo","123456",
                "Av. Paulista",
                "SP","Brasil",
                123456,"Suldeste");
        Office office = new Office();
        office.setCode(1);
        when(repository.findById(1)).thenReturn(Optional.of(office));
        when(repository.save(any())).thenReturn(office);

        Office update = service.updateOffice(1,dto);
        assertNotNull(update);
        assertEquals("SP",update.getState());
        assertEquals("São Paulo",update.getCity());
        assertEquals("123456",update.getPhone());
        assertEquals("Av. Paulista",update.getAddress1());
        assertEquals("Brasil",update.getCountry());
        assertEquals(123456,update.getPostalCode());
        assertEquals("Suldeste",update.getTerritory());


        verify(repository).findById(1);
        verify(repository).save(office);
    }

    @Test
    void testOffices(){
        List<Office> mockedList = List.of(new Office(), new Office());
        when(repository.findAllOffice()).thenReturn(mockedList);

        List<Office> returned = service.Offices();
        assertEquals(2,returned.size());
        verify(repository).findAllOffice();

    }



}

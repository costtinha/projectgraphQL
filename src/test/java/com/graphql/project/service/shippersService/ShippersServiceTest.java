package com.graphql.project.service.shippersService;

import com.graphql.project.dtos.CreateShipper;
import com.graphql.project.entity.Shippers;
import com.graphql.project.persistance.ShippersRepository;
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

public class ShippersServiceTest {
    @Mock
    private ShippersRepository repository;

    @Mock
    private ShippersMapper mapper;

    @InjectMocks
    private ShippersService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindShippers(){
        List<Shippers> mockedList = List.of(new Shippers(),new Shippers());

        when(repository.findShippersAll()).thenReturn(mockedList);

        List<Shippers> shippers = service.findShippers();

        assertEquals(2,shippers.size());

        verify(repository).findShippersAll();
    }

    @Test
    void testFindShipperById_Success(){
        Shippers shippers = new Shippers();
        shippers.setShipId(1);
        shippers.setCompanyName("Homero");

        when(repository.findById(1)).thenReturn(Optional.of(shippers));

        Shippers found = service.findShipperById(1);

        assertNotNull(found);
        assertEquals("Homero",found.getCompanyName());
        assertEquals(1,found.getShipId());

        verify(repository).findById(1);
    }

    @Test
    void testFindShipperById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,() -> service.findShipperById(99));

        assertEquals("Shipper with id 99 not found",exception.getMessage());

        verify(repository).findById(99);
    }

    @Test
    void testCreateShippers(){
        CreateShipper shipperDto = new CreateShipper(
                "FastTrack Shipping",
                "+1-555-123-4567"
        );
        Shippers shippers = new Shippers();
        shippers.setShipId(1);
        shippers.setCompanyName("FastTrack Shipping");
        when(mapper.shippersDtoToShippers(shipperDto)).thenReturn(shippers);
        when(repository.save(shippers)).thenReturn(shippers);

        Shippers saved = service.createShippers(shipperDto);

        assertEquals(1,saved.getShipId());
        assertEquals("FastTrack Shipping",saved.getCompanyName());
        assertNotNull(saved);

        verify(mapper).shippersDtoToShippers(shipperDto);
        verify(repository).save(shippers);
    }

    @Test
    void testDeleteShippersById(){
        Shippers shippers = new Shippers();
        shippers.setShipId(1);
        shippers.setCompanyName("FastTrack Shipping");
        when(repository.findById(1)).thenReturn(Optional.of(shippers));

        Shippers deleted = service.deleteShippersById(1);

        assertNotNull(deleted);
        assertEquals("FastTrack Shipping",deleted.getCompanyName());
        assertEquals(1,deleted.getShipId());

        verify(repository).findById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void testUpdateShippers(){
        CreateShipper shipperDto = new CreateShipper(
                "FastTrack Shipping",
                "+1-555-123-4567"
        );
        Shippers shipper = new Shippers();
        shipper.setShipId(1);
        when(repository.findById(1)).thenReturn(Optional.of(shipper));
        when(repository.save(any())).thenReturn(shipper);

        Shippers updated = service.updateShippers(1,shipperDto);

        assertEquals("FastTrack Shipping",updated.getCompanyName());
        assertEquals("+1-555-123-4567",updated.getPhoneNumber());
        assertEquals(1,updated.getShipId());

        verify(repository).findById(1);
        verify(repository).save(any());
    }

}

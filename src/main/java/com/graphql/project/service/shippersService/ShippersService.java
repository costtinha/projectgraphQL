package com.graphql.project.service.shippersService;

import com.graphql.project.dtos.CreateShipper;
import com.graphql.project.entity.Shippers;
import com.graphql.project.persistance.ShippersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippersService {
    private final ShippersRepository repository;
    private final ShippersMapper mapper;

    public ShippersService(ShippersRepository repository, ShippersMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Shippers> findShippers() {
        return repository.findAll();

    }

    public Shippers findShipperById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipper with id " + id + " not found"));
    }

    public Shippers createShippers(CreateShipper dto) {
        return repository.save(mapper.shippersDtoToShippers(dto));
    }

    public Shippers deleteShippersById(int id) {
        Shippers shippers = findShipperById(id);
        repository.deleteById(id);
        return shippers;
    }

    public Shippers updateShippers(int id, CreateShipper dto) {
        Shippers shippers = findShipperById(id);
        shippers = mapper.shippersDtoToShippers(dto);
        return repository.save(shippers);
    }
}

package com.graphql.project.service.shippersService;

import com.graphql.project.dtos.CreateShipper;
import com.graphql.project.entity.Shippers;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ShippersMapper {
    public Shippers shippersDtoToShippers(CreateShipper dto){
        Shippers shippers = new Shippers();
        shippers.setCompanyName(dto.companyName());
        shippers.setPhoneNumber(dto.phoneNumber());
        shippers.setShipperOrders(Collections.emptyList());
        return shippers;

    }
}

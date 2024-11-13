package com.example.order.repositories;

import com.example.order.models.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service")
public interface AddressClient {

    @GetMapping("/addresses/address/{addressId}")
    AddressDTO getAddressById(@PathVariable("addressId") Long addressId);
}

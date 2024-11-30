package com.coding.dojo.Ecommerce.services.interf;

import com.coding.dojo.Ecommerce.dto.AddressDto;
import com.coding.dojo.Ecommerce.dto.Response;

public interface AddressService {
    Response saveAndUpdateAddress(AddressDto addressDto);
}
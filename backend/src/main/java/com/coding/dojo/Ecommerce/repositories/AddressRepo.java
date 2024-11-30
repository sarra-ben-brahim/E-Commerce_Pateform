package com.coding.dojo.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.dojo.Ecommerce.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}

package com.ms.repository;

import com.ms.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
   public Address findAddressByUserId (Long userId);
}

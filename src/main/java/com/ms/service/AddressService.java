package com.ms.service;

import com.ms.entities.Address;
import com.ms.exceptions.AddressNotFoundException;
import com.ms.repository.AddressRepository;
import com.ms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Address> getAllAddresses () {
        return addressRepository.findAll();
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

    public Address getAddressById(Long id) throws AddressNotFoundException {
        Optional<Address> result = addressRepository.findById(id);
        if (result.isPresent()) {
           return result.get();
        }
        throw new AddressNotFoundException("Could not find any address by ID " + id);
    }

    public void delete(Long id) throws AddressNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new AddressNotFoundException("Could not find and delete address by ID " + id);
        }
        addressRepository.deleteById(id);
    }











}

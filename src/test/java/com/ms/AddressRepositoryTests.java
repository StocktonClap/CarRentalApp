package com.ms;

import com.ms.entities.Address;
import com.ms.entities.User;
import com.ms.repository.AddressRepository;
import com.ms.repository.UserRepository;
import org.apache.el.lang.ELArithmetic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class AddressRepositoryTests {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
    }

    @Test
    public void testCreateNewAddress() {
        User user = userRepository.findById(2L).get();
        Address savedAddress = new Address();
        savedAddress.setCity("Aleksandrów Kujawski");
        savedAddress.setCountry("Poland");
        savedAddress.setStreet("Sosnowa");
        savedAddress.setPostCode("87-700");
        savedAddress.setUser(user);

        addressRepository.save(savedAddress);

        Assertions.assertThat(savedAddress).isNotNull();
        Assertions.assertThat(savedAddress.getId()).isGreaterThan(0);
    }

    @Test
    public void testAddressList() {
        Iterable<Address> addresses = addressRepository.findAll();
        Assertions.assertThat(addresses).hasSizeGreaterThan(0);

        for (Address address : addresses) {
            System.out.println(address);
        }
    }

    @Test
    public void testUpdateAddress() {
        Long addressId = 1L;
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        Address address = optionalAddress.get();
        address.setStreet("Świerkowa");
        addressRepository.save(address);

        Address updatedAddress = addressRepository.findById(addressId).get();
        Assertions.assertThat(updatedAddress.getStreet()).isEqualTo("Świerkowa");
    }

    @Test
    public void testGetAddress() {
        Long addressId = 1L;
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        Assertions.assertThat(optionalAddress).isPresent();
        System.out.println(optionalAddress.get());
    }

    @Test
    public void testDeleteAddress() {
        Long addressId = 4L;
        addressRepository.deleteById(addressId);

        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        Assertions.assertThat(optionalAddress).isNotPresent();
    }
}
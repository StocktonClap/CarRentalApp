package com.ms.controller;

import com.ms.entities.Address;
import com.ms.exceptions.AddressNotFoundException;
import com.ms.exceptions.UserNotFoundExpection;
import com.ms.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public String showAddressList(Model model) {
        List<Address> addressList = addressService.getAllAddresses();
        model.addAttribute("addressList", addressList);
        return "addresses";
    }

    @PutMapping("/addresses/save")
    public String saveAddress (Address address, RedirectAttributes redirectAttributes) {
        addressService.save(address);
        redirectAttributes.addFlashAttribute("message", "The address has beed saved succesfully!");

        return "redirect:/addresses";
    }

    @GetMapping("/addresses/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Address address = addressService.getAddressById(id);
            model.addAttribute("address", address);
            model.addAttribute("pageTitle", "Edit Address (ID): " +id+")");
            redirectAttributes.addFlashAttribute("message", "The address has beed updated succesfully!");
            return "address_form";
        } catch (AddressNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/addresses";
        }
    }

    @GetMapping("/addresses/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            addressService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Address ID " + id + " has been deleted successfully.");
        } catch (AddressNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/addresses";
    }
}

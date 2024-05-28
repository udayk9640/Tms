package com.uday.tms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.tms.entity.Vendor;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> findById(Long id) {
        return vendorRepository.findById(id);
    }

    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }
}
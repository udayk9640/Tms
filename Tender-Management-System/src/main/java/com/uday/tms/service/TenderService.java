package com.uday.tms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.tms.entity.Tender;

@Service
public class TenderService {
    @Autowired
    private TenderRepository tenderRepository;

    public Tender save(Tender tender) {
        return tenderRepository.save(tender);
    }

    public List<Tender> findAll() {
        return tenderRepository.findAll();
    }

    public Optional<Tender> findById(Long id) {
        return tenderRepository.findById(id);
    }

    public void deleteById(Long id) {
        tenderRepository.deleteById(id);
    }
}
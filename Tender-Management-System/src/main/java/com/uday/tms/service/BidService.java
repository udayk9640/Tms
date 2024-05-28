package com.uday.tms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.tms.entity.Bid;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    public Optional<Bid> findById(Long id) {
        return bidRepository.findById(id);
    }

    public void deleteById(Long id) {
        bidRepository.deleteById(id);
    }
}
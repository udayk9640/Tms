package com.uday.tms.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uday.tms.entity.Bid;
import com.uday.tms.entity.Tender;
import com.uday.tms.service.BidService;

@RestController
@RequestMapping("/api/bids")
public class BidController {
    @Autowired
    private BidService bidService;

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable Long id) {
        return bidService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tenders/{tenderId}")
    public ResponseEntity<Bid> placeBid(@PathVariable Long tenderId, @RequestBody Bid bid) {
        bid.setTender(new Tender());
        bid.getTender().setId(tenderId);
        return ResponseEntity.ok(bidService.save(bid));
    }

    @GetMapping("/vendors/{vendorId}")
    public ResponseEntity<List<Bid>> getBidsByVendorId(@PathVariable Long vendorId) {
        return ResponseEntity.ok(bidService.findAll().stream()
                .filter(bid -> bid.getVendor().getId().equals(vendorId))
                .collect(Collectors.toList()));
    }
}
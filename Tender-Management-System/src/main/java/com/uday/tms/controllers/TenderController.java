package com.uday.tms.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uday.tms.entity.Bid;
import com.uday.tms.entity.Tender;
import com.uday.tms.service.TenderService;
import com.uday.tms.service.BidService;

@RestController
@RequestMapping("/api/tenders")
public class TenderController {
    @Autowired
    private TenderService tenderService;

    @GetMapping
    public ResponseEntity<List<Tender>> getAllTenders() {
        return ResponseEntity.ok(tenderService.findAll());
    }

    @PostMapping
    public ResponseEntity<Tender> createTender(@RequestBody Tender tender) {
        return ResponseEntity.ok(tenderService.save(tender));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tender> getTenderById(@PathVariable Long id) {
        return tenderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tender> updateTender(@PathVariable Long id, @RequestBody Tender tender) {
        if (tenderService.findById(id).isPresent()) {
            tender.setId(id);
            return ResponseEntity.ok(tenderService.save(tender));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTender(@PathVariable Long id) {
        tenderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/bids")
    public ResponseEntity<List<Bid>> getBidsByTenderId(@PathVariable Long id) {
        Optional<Tender> tender = tenderService.findById(id);
        if (tender.isPresent()) {
            return ResponseEntity.ok(tender.get().getBids());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/bids/{bidId}/select")
    public ResponseEntity<Void> selectBid(@PathVariable Long id, @PathVariable Long bidId) {
        Optional<Tender> tender = tenderService.findById(id);
        if (tender.isPresent()) {
            Optional<Bid> bid = tender.get().getBids().stream()
                    .filter(b -> b.getId().equals(bidId))
                    .findFirst();
            if (bid.isPresent()) {
                bid.get().setStatus("selected");
                 bidService.save(bid.get());
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
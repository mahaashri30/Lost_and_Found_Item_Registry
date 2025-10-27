package com.lost_and_found.controller;

import com.lost_and_found.model.Item;
import com.lost_and_found.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/lost-items")
    public ResponseEntity<List<Item>> getLostItems() {
        return ResponseEntity.ok(itemService.getLostItems());
    }

    @GetMapping("/found-items")
    public ResponseEntity<List<Item>> getFoundItems() {
        return ResponseEntity.ok(itemService.getFoundItems());
    }
}
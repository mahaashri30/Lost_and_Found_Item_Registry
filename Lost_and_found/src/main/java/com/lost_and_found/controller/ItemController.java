package com.lost_and_found.controller;

import com.lost_and_found.dto.ItemDto;
import com.lost_and_found.model.Item;
import com.lost_and_found.model.ItemCategory;
import com.lost_and_found.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> reportItem(@Valid @RequestBody ItemDto dto) {
        return ResponseEntity.ok(itemService.reportItem(dto));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemDto dto) {
        return ResponseEntity.ok(itemService.updateItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ItemCategory category,
            @RequestParam(required = false) String location) {
        return ResponseEntity.ok(itemService.searchItems(name, category, location));
    }
}
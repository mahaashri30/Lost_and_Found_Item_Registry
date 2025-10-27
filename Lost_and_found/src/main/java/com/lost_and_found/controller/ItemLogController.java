package com.lost_and_found.controller;

import com.lost_and_found.model.ItemLog;
import com.lost_and_found.repository.ItemLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item-logs")
public class ItemLogController {

    @Autowired
    private ItemLogRepository itemLogRepository;

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<ItemLog>> getLogsForItem(@PathVariable Long itemId) {
        List<ItemLog> logs = itemLogRepository.findByItemItemIdOrderByTimestampDesc(itemId);
        return ResponseEntity.ok(logs);
    }
}
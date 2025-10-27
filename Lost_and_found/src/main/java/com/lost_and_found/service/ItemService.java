package com.lost_and_found.service;

import com.lost_and_found.dto.ItemDto;
import com.lost_and_found.model.*;
import com.lost_and_found.repository.ItemLogRepository;
import com.lost_and_found.repository.ItemRepository;
import com.lost_and_found.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lost_and_found.model.ItemAction;
import com.lost_and_found.model.ItemStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemService {
    @Autowired private ItemRepository itemRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ItemLogRepository itemLogRepository;

    public Item reportItem(ItemDto dto) {
        User reporter = userRepository.findById(dto.getReportedById())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));

        Item item = Item.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .location(dto.getLocation())
                .reportedBy(reporter)
                .build();

        Item saved = itemRepository.save(item);

        itemLogRepository.save(ItemLog.builder()
                .item(saved)
                .action(ItemAction.REPORTED)
                .performedBy(reporter)
                .remarks("Item reported as " + dto.getStatus())
                .build());

        return saved;
    }

    public Item updateItem(Long id, ItemDto dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        User performer = userRepository.findById(dto.getReportedById())
                .orElseThrow(() -> new RuntimeException("Performer not found"));

        // Auto-set resolvedAt when marked RECOVERED
        if (dto.getStatus() == ItemStatus.RECOVERED && item.getStatus() != ItemStatus.RECOVERED) {
            item.setResolvedAt(LocalDateTime.now());
        }

        item.setName(dto.getName());
        item.setCategory(dto.getCategory());
        item.setDescription(dto.getDescription());
        item.setStatus(dto.getStatus());
        item.setLocation(dto.getLocation());

        Item updated = itemRepository.save(item);

        itemLogRepository.save(ItemLog.builder()
                .item(updated)
                .action(ItemAction.UPDATED)
                .performedBy(performer)
                .remarks("Item updated")
                .build());

        return updated;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> searchItems(String name, ItemCategory category, String location) {
        return itemRepository.searchItems(name, category, location);
    }

    public List<Item> getLostItems() {
        return itemRepository.findByStatus(ItemStatus.LOST);
    }

    public List<Item> getFoundItems() {
        return itemRepository.findByStatus(ItemStatus.FOUND);
    }
}
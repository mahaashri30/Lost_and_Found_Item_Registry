package com.lost_and_found.repository;

import com.lost_and_found.model.ItemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemLogRepository extends JpaRepository<ItemLog, Long> {
    List<ItemLog> findByItemItemIdOrderByTimestampDesc(Long itemId);
}
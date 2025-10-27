package com.lost_and_found.repository;

import com.lost_and_found.model.Item;
import com.lost_and_found.model.ItemCategory;
import com.lost_and_found.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(ItemStatus status);
    List<Item> findByCategory(ItemCategory category);
    List<Item> findByLocationContainingIgnoreCase(String location);
    List<Item> findByNameContainingIgnoreCase(String name);

    @Query("SELECT i FROM Item i WHERE " +
            "(:name IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:category IS NULL OR i.category = :category) AND " +
            "(:location IS NULL OR LOWER(i.location) LIKE LOWER(CONCAT('%', :location, '%')))")
    List<Item> searchItems(@Param("name") String name,
                           @Param("category") ItemCategory category,
                           @Param("location") String location);
}
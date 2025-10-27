package com.lost_and_found.dto;

import com.lost_and_found.model.ItemCategory;
import com.lost_and_found.model.ItemStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long itemId;

    @NotBlank(message = "Item name is required")
    private String name;

    private ItemCategory category;

    private String description;

    @NotNull(message = "Status is required")        // ✅ NotNull for enum
    private ItemStatus status;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Reporter ID is required")    // ✅ NotNull for Long
    private Long reportedById;
}
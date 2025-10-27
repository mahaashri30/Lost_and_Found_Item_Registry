package com.lost_and_found.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String contact;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private LocalDateTime joinDate = LocalDateTime.now();

    @OneToMany(mappedBy = "reportedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> reportedItems = new ArrayList<>();

    @OneToMany(mappedBy = "performedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemLog> performedLogs = new ArrayList<>();
}
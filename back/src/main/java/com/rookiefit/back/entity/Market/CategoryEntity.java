package com.rookiefit.back.entity.Market;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id")
        private Long categoryId;
    
        @Column(name = "category_name", unique = true)
        private String categoryName;
    
        @Column(name = "category_created_at")
        @Temporal(TemporalType.TIMESTAMP)
        private Date categoryCreatedAt;
    
        @Column(name = "category_updated_at")
        @Temporal(TemporalType.TIMESTAMP)
        private Date categoryUpdatedAt;
}

package com.rookiefit.back.entity.Market;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.rookiefit.back.dto.request.Market.MarketProductRequestDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.enums.ProductCondition;
import com.rookiefit.back.entity.enums.SaleStatus;
import com.rookiefit.back.entity.enums.ShippingMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "market_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_list_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MarketItemListEntity marketItemList;  // `MarketItemList`와 연관

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;  // `Category`와 연관

    @Column(name = "item_image_id")
    private String itemImageId;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price", precision = 10, scale = 2)
    private BigDecimal productPrice;

    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_method", nullable = false)
    private ShippingMethod shippingMethod;  // Shipping method (direct, delivery, both)

    @Enumerated(EnumType.STRING)
    @Column(name = "product_condition", nullable = false)
    private ProductCondition productCondition;  // 상품 상태 (새 상품, 중고 등)

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    @Column(name = "product_created_at")
    private String productCreatedAt;

    @Column(name = "product_updated_at")
    private String productUpdatedAt;

    @Column(name = "product_seller")
    private String productSeller;

    public MarketProductsEntity(MarketProductRequestDto dto, MarketItemListEntity marketItemListEntity, UserProfileEntity user) {
        this.productDescription = dto.getProductDescription();
        this.productPrice = dto.getProductPrice();
        this.location = dto.getLocation();
        this.shippingMethod = ShippingMethod.valueOf(dto.getShippingMethod().toUpperCase());
        this.productCondition = ProductCondition.valueOf(dto.getProductCondition().toUpperCase());
        this.saleStatus = SaleStatus.valueOf(dto.getSaleStatus().toUpperCase());
        this.productCreatedAt = dto.getProductCreatedAt();
        this.productUpdatedAt = dto.getProductUpdatedAt();
        this.productSeller = user.getUserNickname();
        this.marketItemList = marketItemListEntity; // MarketItemListEntity와 연관
    }

    public void update(MarketProductRequestDto dto, UserProfileEntity user) {
        this.productPrice = dto.getProductPrice();
        this.location = dto.getLocation();
        this.saleStatus = SaleStatus.valueOf(dto.getSaleStatus().toUpperCase());
        this.productUpdatedAt = dto.getProductUpdatedAt();
        this.productSeller = user.getUserNickname();
    }
}
package com.rookiefit.back.entity.Market;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "market_item_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketItemListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_list_id")
    private Long marketListId;

    @Column(name = "market_item_title")
    private String marketItemTitle;

    @Column(name = "market_item_image_url")
    private String marketItemImageUrl;

    @Column(name = "is_sold")
    private boolean isSold;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToOne(mappedBy = "marketItemList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private MarketProductsEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserProfileEntity userProfile;

    public MarketItemListEntity(MarketItemListRequestDto dto, UserProfileEntity userProfile) {
        this.marketItemTitle = dto.getMarketItemTitle();
        this.marketItemImageUrl = dto.getMarketItemImageUrl();
        this.createdAt = dto.getCreatedAt()!= null ? dto.getCreatedAt() : new Date();
        this.updatedAt = dto.getUpdatedAt();
        this.userProfile = userProfile;
        System.out.println(userProfile.getUserProfileId());
    }
}

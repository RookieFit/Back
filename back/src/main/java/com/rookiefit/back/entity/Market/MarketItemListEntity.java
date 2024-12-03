package com.rookiefit.back.entity.Market;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
import jakarta.persistence.OneToMany;
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
    private String createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private String updatedAt;

    @OneToOne(mappedBy = "marketItemList", cascade = CascadeType.ALL)
    private MarketProductsEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserProfileEntity userProfile;

    @OneToMany(mappedBy = "marketItemList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemImageEntity> itemImages = new ArrayList<>();  // 이미지 목록 추가

    public void addItemImage(ItemImageEntity itemImage) {
        this.itemImages.add(itemImage);
    }

    public MarketItemListEntity(MarketItemListRequestDto dto, UserProfileEntity userProfile) {
        this.marketItemTitle = dto.getMarketItemTitle();
        this.marketItemImageUrl = dto.getMarketItemImageUrl();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
        this.userProfile = userProfile;
    }

    public void update(MarketItemListRequestDto dto) {
        this.marketItemTitle = dto.getMarketItemTitle();
        this.marketItemImageUrl = dto.getMarketItemImageUrl();
        this.isSold = dto.isSold();
        this.updatedAt = dto.getUpdatedAt();
    }
}

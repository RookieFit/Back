package com.rookiefit.back.entity.Market;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT 설정
    @Column(name = "item_image_id")
    private Long itemImageId;

    @Column(name = "item_image_uri")
    private String itemImageUri;  // 이미지 URI (주소)

    @ManyToOne(fetch = FetchType.LAZY)  // Many to One 관계 설정 (다수의 이미지는 하나의 마켓게시물과 관련)
    @JoinColumn(name = "market_list_id", referencedColumnName = "market_list_id", nullable = false)
    private MarketItemListEntity marketItemList;

    public ItemImageEntity(String itemImageUri) {
        this.itemImageUri = itemImageUri;
    }
}

package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetRequestModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.AssetStatus;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.AssetEntity;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AssetDto {
    private String assetUid;
    private String assetName;
    private String category;
    private BigDecimal purchaseCost;
    private String assetStatus;
    private int usefulLife;


    public static AssetDto toAssetDto(AssetEntity entity) {
        if (entity == null) {
            return null;
        }
        return AssetDto.builder()
                .assetUid(entity.getAssetUid())
                .assetName(entity.getAssetName())
                .category(entity.getCategory())
                .purchaseCost(entity.getPurchaseCost())
                .assetStatus(entity.getAssetStatus().getStatus())
                .usefulLife(entity.getAssetUsefulLife())
                .build();
    }

    public static AssetEntity toAssetEntity(AssetRequestModel asset) {
        if (asset == null) {
            throw new IllegalArgumentException("An Error occurred while registering the asset");
        }
        String assetUid = "ASSE"+ UUID.randomUUID().toString().substring(0, 6).replace("-","").toUpperCase();
        return AssetEntity.builder()
                .assetUid(assetUid)
                .assetName(asset.getAssetName())
                .assetStatus(AssetStatus.REGISTERED)
                .category(asset.getAssetCategory())
                .purchaseCost(asset.getPurchaseCost())
                .assetUsefulLife(asset.getUsefulLife())
                .build();
    }
}

package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.AssetStatus;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.AssetEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetSummaryDto {

    private String assetUid;
    private String assetName;
    private AssetStatus assetStatus;


    public static AssetSummaryDto toAssetSummaryDto(AssetEntity entity) {
        return AssetSummaryDto.builder()
                .assetUid(entity.getAssetUid())
                .assetName(entity.getAssetName())
                .assetStatus(entity.getAssetStatus())
                .build();
    }
}

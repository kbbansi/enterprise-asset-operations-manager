package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.AssetStatus;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department.DepartmentSummaryDto;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.AssetEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetDepartmentDto {
    private String assetUid;
    private String assetName;
    private AssetStatus assetStatus;
    private String category;
    private BigDecimal purchaseCost;
    private int assetUsefulLife;

    private DepartmentSummaryDto department;

    public static AssetDepartmentDto toAssetDepartmentDto(AssetEntity assetEntity) {
        var departmentDetail = assetEntity.getDepartment() == null ? null : DepartmentSummaryDto.toDepartmentSummaryDto(assetEntity.getDepartment());
        return AssetDepartmentDto.builder()
                .assetName(assetEntity.getAssetName())
                .assetStatus(assetEntity.getAssetStatus())
                .assetUsefulLife(assetEntity.getAssetUsefulLife())
                .assetUid(assetEntity.getAssetUid())
                .category(assetEntity.getCategory())
                .purchaseCost(assetEntity.getPurchaseCost())
                .department(departmentDetail)
                .build();
    }
}

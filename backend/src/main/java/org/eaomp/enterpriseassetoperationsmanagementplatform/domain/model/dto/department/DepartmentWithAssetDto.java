package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetSummaryDto;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.DepartmentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentWithAssetDto {

    private String departmentUid;
    private String departmentName;

    private List<AssetSummaryDto> assets;  // assigned assets

    public static DepartmentWithAssetDto toDepartmentWithAssetDto(DepartmentEntity departmentEntity) {
        var assets = departmentEntity.getAssets().stream().map(AssetSummaryDto::toAssetSummaryDto).collect(Collectors.toList());
        return DepartmentWithAssetDto.builder()
                .departmentUid(departmentEntity.getDepartmentUid())
                .departmentName(departmentEntity.getDepartmentName())
                .assets(assets)
                .build();
    }
}

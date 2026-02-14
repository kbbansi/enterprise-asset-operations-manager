package org.eaomp.enterpriseassetoperationsmanagementplatform.service;

import lombok.RequiredArgsConstructor;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetRequestModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.AssetStatus;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDto;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.AssetEntity;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.DepartmentEntity;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.repo.AssetDataRepo;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.repo.DepartmentDataRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDto.toAssetDto;

@Service
@RequiredArgsConstructor
@Transactional
public class AssetAssignmentService {

    private final AssetDataRepo assetDataRepo;
    private final DepartmentDataRepo departmentDataRepo;

    public AssetDto assignAsset(String assetUid, AssetRequestModel.AssignAssetRequestModel asset) {

        if (asset.getDepartmentUid() == null || asset.getDepartmentUid().isBlank()) {
            throw new IllegalArgumentException("Please provide a valid department name");
        }


        AssetEntity assetEntity = assetDataRepo
                .getAssetEntityByAssetUid(assetUid)
                .orElseThrow(() -> new RuntimeException("No asset with uid: " + assetUid + " found"));

        //Cannot assign disposed asset
        if (assetEntity.getAssetStatus() == AssetStatus.DISPOSED) {
            throw new RuntimeException("Cannot assign an asset that has been disposed");
        }

        DepartmentEntity department = departmentDataRepo
                .getDepartmentEntityByDepartmentUid(asset.getDepartmentUid())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        assetEntity.setDepartment(department);
        assetEntity.setAssetStatus(AssetStatus.ASSIGNED);
//        AssetEntity assignedAsset = assetDataRepo.save(assetEntity); not needed when using @Transactional
        return toAssetDto(assetEntity);
    }

    @Transactional
    public AssetDto disposeAsset(String assetUid) {

        AssetEntity assetEntity = assetDataRepo
                .getAssetEntityByAssetUid(assetUid)
                .orElseThrow(() ->
                        new RuntimeException("No asset with uid: " + assetUid + " found")
                );

        if (assetEntity.getAssetStatus() != AssetStatus.ASSIGNED) {
            throw new RuntimeException(
                    "Only ASSIGNED assets can be disposed. Current status: " + assetEntity.getAssetStatus()
            );
        }

        assetEntity.setDepartment(null);
        assetEntity.setAssetStatus(AssetStatus.DISPOSED);

        return toAssetDto(assetEntity);
    }
}

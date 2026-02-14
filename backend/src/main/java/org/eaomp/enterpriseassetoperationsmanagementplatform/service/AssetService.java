package org.eaomp.enterpriseassetoperationsmanagementplatform.service;

import lombok.RequiredArgsConstructor;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetRequestModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDepartmentDto;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDto;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.AssetEntity;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.repo.AssetDataRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDto.toAssetDto;
import static org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDto.toAssetEntity;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetDataRepo assetDataRepo;
    public AssetDto createAsset(AssetRequestModel asset) {
        //basic validation
        validate(asset);
        AssetEntity assetEntity = toAssetEntity(asset);
        var createdAsset = assetDataRepo.save(assetEntity);
        return toAssetDto(createdAsset);
    }

    public List<AssetDto> getAllAssets() {
        return assetDataRepo.findAll().stream().map(AssetDto::toAssetDto).toList();
    }

    public AssetDto getAsset(String assetUid) {
        var assetEntity = assetDataRepo.getAssetEntityByAssetUid(assetUid).orElseThrow(() -> new RuntimeException("No asset with uid: " + assetUid + " found"));
        return toAssetDto(assetEntity);

    }

    private void validate(AssetRequestModel asset) {
        if (asset.getAssetName() == null || asset.getAssetName().isBlank()) {
            throw new IllegalArgumentException("Asset name is required");
        }

        if (asset.getAssetCategory() == null || asset.getAssetCategory().isBlank()) {
            throw new IllegalArgumentException("Asset Category is required");
        }

        if (asset.getPurchaseCost() == null || asset.getPurchaseCost().equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Asset Purchase Cost cannot be Zero");
        }

        if (asset.getPurchaseCost().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Please supply a valid non negative value for Asset Purchase Cose");
        }

        if (asset.getUsefulLife() <= 0) {
            // just log here.......
        }
    }

    public AssetDepartmentDto getAssetDetails(String assetUid) {
        AssetEntity assetEntity = assetDataRepo.getAssetEntityByAssetUid(assetUid).orElseThrow(() -> new RuntimeException("No asset with uid: " + assetUid + " found"));
        return AssetDepartmentDto.toAssetDepartmentDto(assetEntity);
    }
}

package org.eaomp.enterpriseassetoperationsmanagementplatform.api;

import lombok.RequiredArgsConstructor;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetDetailResponseModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetRequestModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetResponseModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.service.AssetAssignmentService;
import org.eaomp.enterpriseassetoperationsmanagementplatform.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetResponseModel.success;

@RestController
@RequestMapping("/api/asset")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AssetController {


    private final AssetService assetService;
    private final AssetAssignmentService assetAssignmentService;

    @PostMapping
    public ResponseEntity<AssetResponseModel> createNewAsset(@RequestBody @Validated AssetRequestModel req) {
        var assetDto = assetService.createAsset(req);
        var apiRes = success(assetDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @PutMapping("/{assetUid}/assign")
    public ResponseEntity<AssetResponseModel> assignAsset(@PathVariable String assetUid, @RequestBody AssetRequestModel.AssignAssetRequestModel req) {
        var assetDto = assetAssignmentService.assignAsset(assetUid, req);
        var apiRes = success(assetDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<AssetResponseModel> getAllAssets() {
        var assetList = assetService.getAllAssets();
        var apiRes = success(assetList);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @PutMapping("/{assetUid}/dispose")
    public ResponseEntity<AssetResponseModel> disposeAsset(@PathVariable String assetUid) {
        var assetDto = assetAssignmentService.disposeAsset(assetUid);
        var apiRes = success("Asset: " + assetDto.getAssetName(), assetDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @GetMapping("/{assetUid}")
    public  ResponseEntity<AssetResponseModel> getAsset(@PathVariable String assetUid) {
        var assetDto = assetService.getAsset(assetUid);
        var apiRes = success(assetDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @GetMapping("/{assetUid}/details")
    public ResponseEntity<AssetDetailResponseModel> getAssetDetails(@PathVariable String assetUid) {
        var assetDto = assetService.getAssetDetails(assetUid);
        var apiRes = AssetDetailResponseModel.success(assetDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

}

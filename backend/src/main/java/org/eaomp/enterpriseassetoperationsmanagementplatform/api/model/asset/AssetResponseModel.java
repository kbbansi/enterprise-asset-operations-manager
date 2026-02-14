package org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetResponseModel {
    private int statusCode;
    private String message;
    private AssetDto data;
    private List<AssetDto> dataList;
    
    public static AssetResponseModel success(AssetDto data) {
        return AssetResponseModel.builder()
                .statusCode(200)
                .message("Success")
                .data(data)
                .build();
    }
    
    public static AssetResponseModel success(List<AssetDto> dataList) {
        return AssetResponseModel.builder()
                .statusCode(200)
                .message("Success")
                .dataList(dataList)
                .build();
    }
    
    public static AssetResponseModel success(String message, AssetDto data) {
        return AssetResponseModel.builder()
                .statusCode(200)
                .message(message)
                .data(data)
                .build();
    }
    
    public static AssetResponseModel success(String message, List<AssetDto> dataList) {
        return AssetResponseModel.builder()
                .statusCode(200)
                .message(message)
                .dataList(dataList)
                .build();
    }
    
    public static AssetResponseModel error(String message) {
        return AssetResponseModel.builder()
                .statusCode(400)
                .message(message)
                .build();
    }
    
    public static AssetResponseModel error(int statusCode, String message) {
        return AssetResponseModel.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }


}

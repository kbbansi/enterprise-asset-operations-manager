package org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.asset.AssetDepartmentDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetDetailResponseModel {
    private int statusCode;
    private String message;
    private AssetDepartmentDto data;

    public static AssetDetailResponseModel success(int statusCode, String message, AssetDepartmentDto data) {
        return AssetDetailResponseModel.builder()
                .statusCode(statusCode)
                .message(message)
                .data(data)
                .build();
    }

    public static AssetDetailResponseModel success(AssetDepartmentDto data) {
        return AssetDetailResponseModel.builder()
                .statusCode(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static  AssetDetailResponseModel error(int statusCode, String message) {
        return AssetDetailResponseModel.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }

    public static  AssetDetailResponseModel error(String message) {
        return AssetDetailResponseModel.builder()
                .statusCode(400)
                .message(message)
                .build();
    }


}

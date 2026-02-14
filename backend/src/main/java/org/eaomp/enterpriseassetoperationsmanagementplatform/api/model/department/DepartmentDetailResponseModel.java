package org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department;


import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department.DepartmentWithAssetDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDetailResponseModel {
    private int statusCode;
    private String message;
    private DepartmentWithAssetDto data;

    public static DepartmentDetailResponseModel success(int statusCode, String message, DepartmentWithAssetDto data) {
        return DepartmentDetailResponseModel.builder()
                .statusCode(statusCode)
                .message(message)
                .data(data)
                .build();
    }

    public static DepartmentDetailResponseModel success(DepartmentWithAssetDto data) {
        return DepartmentDetailResponseModel.builder()
                .statusCode(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static DepartmentDetailResponseModel error(int statusCode, String message) {
        return DepartmentDetailResponseModel.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }

    public static DepartmentDetailResponseModel error(String message) {
        return DepartmentDetailResponseModel.builder()
                .statusCode(400)
                .message(message)
                .build();
    }
}

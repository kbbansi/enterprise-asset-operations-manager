package org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department.DepartmentDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponseModel {
    private int statusCode;
    private String message;
    private DepartmentDto data;
    private List<DepartmentDto> dataList;


    public static DepartmentResponseModel success(DepartmentDto data) {
        return DepartmentResponseModel.builder()
                .statusCode(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static DepartmentResponseModel success(List<DepartmentDto> dataList) {
        return DepartmentResponseModel.builder()
                .statusCode(200)
                .message("Success")
                .dataList(dataList)
                .build();
    }

    public static DepartmentResponseModel success(String message, List<DepartmentDto> dataList){
        return DepartmentResponseModel.builder()
                .statusCode(200)
                .message(message)
                .dataList(dataList)
                .build();
    }

    public static DepartmentResponseModel error(String message) {
        return DepartmentResponseModel.builder()
                .statusCode(400)
                .message(message)
                .build();
    }

    public static DepartmentResponseModel error(int statusCode, String message) {
        return DepartmentResponseModel.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}

package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department;


import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department.DepartmentRequestModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.DepartmentEntity;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private String departmentUid;
    private String departmentName;


    public static DepartmentDto toDepartmentDto(DepartmentEntity entity) {
        if (entity == null) {
            return null;
        }

        return DepartmentDto.builder()
                .departmentUid(entity.getDepartmentUid())
                .departmentName(entity.getDepartmentName())
                .build();
    }


    public static DepartmentEntity toDepartmentEntity(DepartmentRequestModel department) {
        if (department == null) {
            throw new IllegalArgumentException("An Error occurred while creating the deparment");
        }

        String departmentUid = "DEPT" + UUID.randomUUID().toString().substring(0, 6).replace("-","").toUpperCase();

        return DepartmentEntity.builder()
                .departmentUid(departmentUid)
                .departmentName(department.getDepartmentName())
                .build();
    }
}

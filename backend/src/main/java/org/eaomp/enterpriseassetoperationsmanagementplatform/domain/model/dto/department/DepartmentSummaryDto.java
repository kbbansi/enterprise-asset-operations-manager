package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department;

import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.DepartmentEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentSummaryDto {

    private String departmentUid;
    private String departmentName;



    public static DepartmentSummaryDto toDepartmentSummaryDto(DepartmentEntity department) {
        return DepartmentSummaryDto.builder()
                .departmentUid(department.getDepartmentUid())
                .departmentName(department.getDepartmentName())
                .build();
    }
}

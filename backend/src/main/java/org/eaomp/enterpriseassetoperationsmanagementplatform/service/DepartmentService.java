package org.eaomp.enterpriseassetoperationsmanagementplatform.service;


import lombok.RequiredArgsConstructor;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department.DepartmentRequestModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department.DepartmentDto;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department.DepartmentWithAssetDto;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.DepartmentEntity;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.repo.DepartmentDataRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.dto.department.DepartmentDto.*;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentDataRepo departmentDataRepo;

    public DepartmentDto createDepartment(DepartmentRequestModel departmentRequestModel) {
        validateDepartment(departmentRequestModel);

        DepartmentEntity departmentEntity = toDepartmentEntity(departmentRequestModel);

        DepartmentEntity createdDepartment = departmentDataRepo.save(departmentEntity);


        return toDepartmentDto(createdDepartment);
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartment(String departmentUid) {
        DepartmentEntity departmentEntity = departmentDataRepo
                .getDepartmentEntityByDepartmentUid(departmentUid)
                .orElseThrow(() -> new RuntimeException("No department with uid: " + departmentUid + " found"));;
        return toDepartmentDto(departmentEntity);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        return departmentDataRepo.findAll().stream()
                .map(DepartmentDto::toDepartmentDto)
                .collect(Collectors.toList());
    }

    private void validateDepartment(DepartmentRequestModel departmentRequestModel) {
     if (departmentRequestModel == null) {
         throw new IllegalArgumentException("Department request cannot be null");
     }

     if (departmentRequestModel.getDepartmentName() == null || departmentRequestModel.getDepartmentName().trim().isEmpty()) {
         throw new IllegalArgumentException("Please provide a valid department name");
     }
    }

    public DepartmentWithAssetDto getDetails(String departmentUid) {

        DepartmentEntity departmentEntity = departmentDataRepo
                .getDepartmentEntityByDepartmentUid(departmentUid)
                .orElseThrow(() -> new RuntimeException("No department with uid: " + departmentUid + " found"));;

        return DepartmentWithAssetDto.toDepartmentWithAssetDto(departmentEntity);
    }
}

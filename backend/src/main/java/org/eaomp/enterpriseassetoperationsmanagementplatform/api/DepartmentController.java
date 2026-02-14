package org.eaomp.enterpriseassetoperationsmanagementplatform.api;

import lombok.RequiredArgsConstructor;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department.DepartmentDetailResponseModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department.DepartmentRequestModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department.DepartmentResponseModel;
import org.eaomp.enterpriseassetoperationsmanagementplatform.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.department.DepartmentResponseModel.success;


@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseModel> create(@RequestBody DepartmentRequestModel dto) {
        var departmentDto = departmentService.createDepartment(dto);
        var apiRes = success(departmentDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @GetMapping("/{departmentUid}")
    public ResponseEntity<DepartmentResponseModel> getByUid(@PathVariable String departmentUid) {
        var departmentDto = departmentService.getDepartment(departmentUid);
        var apiRes = success(departmentDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<DepartmentResponseModel> getAll() {
        var departmentDtoList = departmentService.getAllDepartments();
        var apiRes = success(departmentDtoList);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }

    @GetMapping("/{departmentUid}/details")
    public ResponseEntity<DepartmentDetailResponseModel> getDetails(@PathVariable String departmentUid) {
        var departmentDto = departmentService.getDetails(departmentUid);
        var apiRes = DepartmentDetailResponseModel.success(departmentDto);
        return new ResponseEntity<>(apiRes, HttpStatus.OK);
    }
}

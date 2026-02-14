package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.repo;

import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentDataRepo extends JpaRepository<DepartmentEntity, Long> {

    Optional<DepartmentEntity> getDepartmentEntityByDepartmentUid(String departmentUid);

    @Query("Select d from DepartmentEntity d left join fetch d.assets where d.departmentUid = :departmentUid")
    Optional<DepartmentEntity> getDepartmentEntityByDepartmentUidWithAssets(String departmentUid);
}

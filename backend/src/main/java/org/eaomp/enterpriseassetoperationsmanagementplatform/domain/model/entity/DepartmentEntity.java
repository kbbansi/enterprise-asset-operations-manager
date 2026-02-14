package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department_entity")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "department_uid", nullable = false)
    private String departmentUid;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<AssetEntity> assets;

}
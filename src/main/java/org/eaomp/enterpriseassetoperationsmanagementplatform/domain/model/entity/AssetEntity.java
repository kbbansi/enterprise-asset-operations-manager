package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.AssetStatus;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name ="asset_uid")
    private String assetUid;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "category")
    private String category;

    @Column(name = "purchase_cost")
    private BigDecimal purchaseCost;

    @Column(name = "asset_status")
    @Enumerated(EnumType.STRING)
    private AssetStatus assetStatus;

    @Column(name = "asset_useful_life")
    private int assetUsefulLife;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true)
    private DepartmentEntity department;

}

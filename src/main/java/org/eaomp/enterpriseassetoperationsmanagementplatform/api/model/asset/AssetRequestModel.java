package org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AssetRequestModel {
    private String assetName;
    private String assetCategory;
    private int usefulLife;
    private BigDecimal purchaseCost;


    @Getter
    @Setter
    public static class AssignAssetRequestModel {
        private String departmentUid;
    }
}

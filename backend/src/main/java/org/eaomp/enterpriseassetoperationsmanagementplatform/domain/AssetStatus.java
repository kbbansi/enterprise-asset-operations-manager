package org.eaomp.enterpriseassetoperationsmanagementplatform.domain;

public enum AssetStatus {
    REGISTERED("REGISTERED"),
    ASSIGNED("ASSIGNED"),
    DISPOSED("DISPOSED");

    final  String status;
    AssetStatus(String value) {
        this.status = value;
    }
    public String getStatus() {
        return status;
    }
}

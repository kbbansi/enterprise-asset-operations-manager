package org.eaomp.enterpriseassetoperationsmanagementplatform.domain.repo;

import org.eaomp.enterpriseassetoperationsmanagementplatform.domain.model.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetDataRepo extends JpaRepository<AssetEntity, Long> {

    Optional<AssetEntity> getAssetEntityByAssetUid(String assetUid);

    @Query("Select a from AssetEntity a left join fetch a.department where a.assetUid = :assetUid")
    Optional<AssetEntity> getAssetEntityByAssetUidWithDepartment(String assetUid);

}

package com.innogent.pantry_mind.repository;

import com.innogent.pantry_mind.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem,Long> {

}

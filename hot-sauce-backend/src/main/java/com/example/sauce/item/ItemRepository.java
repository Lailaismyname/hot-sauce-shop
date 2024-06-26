package com.example.sauce.item;

import com.example.sauce.ingredient.Ingredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
  List<Item> findByIngredientsIn(List<Ingredient> ingredients);

  List<Item> findByHeatLevel(HeatLevel heatLevel);
}

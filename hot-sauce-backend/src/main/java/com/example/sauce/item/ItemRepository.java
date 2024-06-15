package com.example.sauce.item;

import com.example.sauce.ingredient.Ingredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIngredientsIn(List<Ingredient> ingredients);
}

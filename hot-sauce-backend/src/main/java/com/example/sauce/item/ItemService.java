package com.example.sauce.item;

import com.example.sauce.ingredient.Ingredient;
import com.example.sauce.ingredient.IngredientService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  // private final Logger logger;
  private final ItemRepository itemRepository;
  private final IngredientService ingredientService;

  public ItemService(ItemRepository itemRepository, IngredientService ingredientService) {
    this.itemRepository = itemRepository;
    this.ingredientService = ingredientService;
  }

  public List<Item> getAll(
      List<String> ingredients, List<String> heatLevels, Double min, Double max) {
    Specification<Item> specification = Specification.where(null);

    if (ingredients != null && !ingredients.isEmpty()) {
      List<Ingredient> ingredientList =
          ingredients.stream().map(ingredientService::getByName).toList();
      specification = specification.and(ItemSpecification.hasIngredients(ingredientList));
    }

    if (heatLevels != null) {
      List<HeatLevel> heatLevelList =
          heatLevels.stream().map(heatLevel -> HeatLevel.valueOf(heatLevel.toUpperCase())).toList();
      specification = specification.and(ItemSpecification.hasHeatLevel(heatLevelList));
    }
    if (min != null || max != null) {
      specification = specification.and(ItemSpecification.hasPriceRangeMinMax(min, max));
    }

    return itemRepository.findAll(specification);
  }

  public Item getById(Long id) {
    return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public List<Item> getByIngredients(List<String> ingredients) {
    // string to ingredient
    List<Ingredient> ingredientList =
        ingredients.stream().map(ingredientService::getByName).collect(Collectors.toList());
    ;
    return itemRepository.findByIngredientsIn(ingredientList);
  }

  // create, update and delete
  public Item save(Item item) {
    return itemRepository.save(item);
  }

  public Item update(Item item) {
    return itemRepository.save(item);
  }

  public void delete(Long id) {
    Optional<Item> item = itemRepository.findById(id);
    if (item.isEmpty()) throw new EntityNotFoundException();
    itemRepository.deleteById(id);
  }

  public void patch(Long id, Item patchItem) {
    Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    if (patchItem.getIngredients() != null) {
      item.addIngredients(patchItem.getIngredients());
    }
    if (patchItem.getName() != null) {
      item.setName(patchItem.getName());
    }
    if (patchItem.getDescription() != null) {
      item.setDescription(patchItem.getDescription());
    }
    if (patchItem.getPrice() != null) {
      item.setPrice(patchItem.getPrice());
    }
    if (patchItem.getHeatLevel() != null) {
      System.out.println(patchItem.getHeatLevel());
      item.setHeatLevel(patchItem.getHeatLevel());
    }
    itemRepository.save(item);
    // TODO in welke gevallen moet ik een exception throwen?
  }
}

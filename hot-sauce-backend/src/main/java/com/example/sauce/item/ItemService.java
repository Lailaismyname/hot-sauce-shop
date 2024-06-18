package com.example.sauce.item;

import com.example.sauce.ingredient.Ingredient;
import com.example.sauce.ingredient.IngredientService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  private final ItemRepository itemRepository;
  private final IngredientService ingredientService;

  public ItemService(ItemRepository itemRepository, IngredientService ingredientService) {
    this.itemRepository = itemRepository;
    this.ingredientService = ingredientService;
  }

  public List<Item> getAll() {
    return itemRepository.findAll();
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

  public List<Item> getByHeatLevel(String heatlevel) {
    return getAll().stream()
        .filter(item -> item.getHeatLevel() == HeatLevel.valueOf(heatlevel.toUpperCase()))
        .toList();
  }

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

  // TODO Patch
}

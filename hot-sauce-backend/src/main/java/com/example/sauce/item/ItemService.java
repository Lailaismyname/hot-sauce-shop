package com.example.sauce.item;

import com.example.sauce.ingredient.Ingredient;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<Item> getAll() {
    return itemRepository.findAll();
  }

  public Item getById(Long id) {
    return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public List<Item> getByIngredients(List<Ingredient> ingredients) {
    return itemRepository.findByIngredientsIn(ingredients);
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

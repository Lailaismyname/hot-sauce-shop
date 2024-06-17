package com.example.sauce.item;

import com.example.sauce.ingredient.IngredientService;
import com.example.sauce.routes.ControllerRoutes;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.ITEM_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class ItemController {
  private final ItemService itemService;
  private final IngredientService ingredientService;

  public ItemController(ItemService itemService, IngredientService ingredientService) {
    this.itemService = itemService;
    this.ingredientService = ingredientService;
  }

  @GetMapping
  public ResponseEntity<List<Item>> getAll(
      @RequestParam(required = false) List<String> ingredients,
      @RequestParam(required = false) List<String> spiceLevels) {
    // if no query parameters are provided, return all.
    if (ingredients == null && spiceLevels == null) return ResponseEntity.ok(itemService.getAll());

    Set<Item> items = new HashSet<>();

    if (ingredients != null) {
      List<Item> filteredItems = itemService.getByIngredients(ingredients);
      items.addAll(filteredItems);
    }

    if (spiceLevels != null) {
      List<Item> filteredItems = itemService.getBySpiceLevel(spiceLevels);
      items.addAll(filteredItems);
      // bijna nu filter voeg ik alles toe wat voldoet aan de filter. Maar ik moet ook nog de items
      // weg filteren die niet aan alle 2 de eisen voldoen
    }

    return ResponseEntity.ok(new ArrayList<>(items));
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Item> getById(@PathVariable Long id) {
    return ResponseEntity.ok(itemService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Item> item(@RequestBody Item item) {
    Item savedItem = itemService.save(item);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedItem.getId())
            .toUri();

    return ResponseEntity.created(location).body(savedItem);
  }

  @PutMapping
  public ResponseEntity<Item> update(@RequestBody Item item) {
    Item updateItem = itemService.update(item);
    return ResponseEntity.ok(updateItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Item> delete(@PathVariable Long id) {
    itemService.delete(id);
    return ResponseEntity.ok().build();
  }

  // TODO Patch
}

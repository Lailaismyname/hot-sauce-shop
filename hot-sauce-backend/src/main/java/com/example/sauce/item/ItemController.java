package com.example.sauce.item;

import com.example.sauce.ingredient.Ingredient;
import com.example.sauce.ingredient.IngredientService;
import com.example.sauce.routes.ControllerRoutes;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
      @RequestParam(required = false) List<String> ingredients) {
    return ResponseEntity.ok(itemService.getAll());
  }

  @GetMapping("/filter")
  public ResponseEntity<List<Item>> getFiltered(
      @RequestParam(required = false) List<String> ingredients,
      @RequestParam(required = false) List<String> spiceLevel) {
    // klopt nog niet helemaal, maar getting there!
    List<Ingredient> ingredientList =
        ingredients.stream().map(ingredientService::getByName).toList();

    List<SpiceLevel> spiceList =
        spiceLevel.stream().map(String::toUpperCase).map(SpiceLevel::valueOf).toList();

    List<Item> filteredList = new ArrayList<>();
    filteredList.addAll(itemService.getBySpiceLevel(spiceList));
    filteredList.addAll(itemService.getByIngredients(ingredientList));

    return ResponseEntity.ok(filteredList);
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

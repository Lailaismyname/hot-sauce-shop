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

  public ItemController(ItemService itemService, IngredientService ingredientService) {
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity<List<Item>> getAll(
      @RequestParam(required = false) List<String> ingredients,
      @RequestParam(required = false)
          List<String> heatlevel) // case sensitive!! ook in de url als query param
      {
    // TODO als nu filtered het en op ingredient en op heatlevel dan laat ie beide zien, maar de
    // filter moet alleen dingen zien de aan beide filters voldoen
    if (ingredients == null && heatlevel == null) return ResponseEntity.ok(itemService.getAll());

    Set<Item> items = new HashSet<>();

    if (ingredients != null) {
      List<Item> filteredItems = itemService.getByIngredients(ingredients);
      items.addAll(filteredItems);
    }

    if (heatlevel != null) {
      List<Item> filteredItems = new ArrayList<>();
      heatlevel.forEach(spiceLevel -> filteredItems.addAll(itemService.getByHeatLevel(spiceLevel)));
      items.addAll(filteredItems);
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

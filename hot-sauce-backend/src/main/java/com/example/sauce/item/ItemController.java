package com.example.sauce.item;

import com.example.sauce.routes.ControllerRoutes;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.ITEM_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class ItemController {
  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity<List<Item>> getAll() {
    return ResponseEntity.ok(itemService.getAll());
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

package com.example.sauce.cartitem;

import com.example.sauce.routes.ControllerRoutes;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.CART_ITEM_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class CartItemController {
  private final CartItemService cartItemService;

  public CartItemController(CartItemService cartItemService) {
    this.cartItemService = cartItemService;
  }

  @GetMapping
  public ResponseEntity<List<CartItem>> getAll() {
    return ResponseEntity.ok(cartItemService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CartItem> getById(@PathVariable Long id) {
    return ResponseEntity.ok(cartItemService.getById(id));
  }

  @PostMapping
  public ResponseEntity<CartItem> save(@RequestBody CartItemDTO cartItem) {
    CartItem savedCartItem = cartItemService.save(cartItem.quantity(), cartItem.id());
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/id/{id}")
            .buildAndExpand(savedCartItem.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedCartItem);
  }

  @PutMapping
  public ResponseEntity<CartItem> update(@RequestBody CartItemDTO cartItem) {
    CartItem updatedCartItem = cartItemService.update(cartItem.quantity(), cartItem.id());
    return ResponseEntity.ok(updatedCartItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CartItem> delete(@PathVariable Long id) {
    cartItemService.delete(id);
    return ResponseEntity.ok().build();
  }

  // TODO Patch
}

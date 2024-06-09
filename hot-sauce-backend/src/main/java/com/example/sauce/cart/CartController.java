package com.example.sauce.cart;

import com.example.sauce.cartitem.CartItem;
import com.example.sauce.routes.ControllerRoutes;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.CART_ROUTE)
@CrossOrigin(origins = {"${client}"})
public class CartController {
  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public ResponseEntity<List<Cart>> getAll() {
    return ResponseEntity.ok(cartService.getAll());
  }

  @GetMapping("/id/{id}")
  public Cart getById(@PathVariable Long id) {
    return cartService.getById(id);
  }

  @PostMapping
  public ResponseEntity<Cart> save(@RequestBody Cart cart) {
    Cart savedCart = cartService.save(cart);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedCart.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedCart);
  }

  @PutMapping("/add-to-cart/{id}")
  public ResponseEntity<Cart> addItemToCart(@PathVariable Long id, @RequestBody CartItem cartItem) {
    Cart updatedCart = cartService.addItemToCart(id, cartItem);
    return ResponseEntity.ok(updatedCart);
  }

  @PutMapping("/delete-from-cart/{id}")
  public ResponseEntity<Cart> deleteItemFromCart(
      @PathVariable Long id, @RequestBody Long cartItemId) {
    Cart updatedCart = cartService.deleteItemFromCart(id, cartItemId);
    return ResponseEntity.ok(updatedCart);
  }

  @PutMapping("/checkout/{id}")
  public ResponseEntity<Cart> checkout(@PathVariable Long id) {
    cartService.checkout(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Cart> delete(@PathVariable Long id) {
    cartService.delete(id);
    return ResponseEntity.ok().build();
  }

  // TODO patch
}

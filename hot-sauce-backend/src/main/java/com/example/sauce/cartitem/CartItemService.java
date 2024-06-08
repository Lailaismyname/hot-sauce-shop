package com.example.sauce.cartitem;

import com.example.sauce.item.Item;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
  private final CartItemRepository cartItemRepository;

  public CartItemService(CartItemRepository cartItemRepository) {
    this.cartItemRepository = cartItemRepository;
  }

  public List<CartItem> getAll() {
    return cartItemRepository.findAll();
  }

  public CartItem getById(Long id) {

    return cartItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public CartItem save(Integer quantity, Item item) {
    return cartItemRepository.save(CartItem.of(quantity, item));
  }

  public CartItem update(CartItem cartItem) {
    return cartItemRepository.save(cartItem);
  }

  public void delete(Long id) {
    Optional<CartItem> cartItem = cartItemRepository.findById(id);
    if (cartItem.isEmpty()) throw new EntityNotFoundException();
  }

  // TODO Patch
}

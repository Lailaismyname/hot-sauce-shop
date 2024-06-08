package com.example.sauce.cart;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  private final CartRepository cartRepository;

  public CartService(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  public List<Cart> getAll() {
    return cartRepository.findAll();
  }

  public Cart getById(Long id) {
    return cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public Cart save(Cart cart) {
    return cartRepository.save(cart);
  }

  public Cart update(Cart cart) {
    return cartRepository.save(cart);
  }

  public void delete(Long id) {
    Optional<Cart> cart = cartRepository.findById(id);
    if (cart.isEmpty()) throw new EntityNotFoundException();
    cartRepository.deleteById(id);
  }

  // TODO Patch
}

package com.example.sauce.cartitem;

import com.example.sauce.customExceptions.InvalidQuantityException;
import com.example.sauce.item.Item;
import com.example.sauce.item.ItemService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
  private final CartItemRepository cartItemRepository;
  private final ItemService itemService;

  public CartItemService(CartItemRepository cartItemRepository, ItemService itemService) {
    this.cartItemRepository = cartItemRepository;
    this.itemService = itemService;
  }

  public List<CartItem> getAll() {
    return cartItemRepository.findAll();
  }

  public CartItem getById(Long id) {

    return cartItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public CartItem save(Integer quantity, Long id) {
    Item item = itemService.getById(id);
    return cartItemRepository.save(CartItem.of(quantity, item));
  }

  public CartItem update(Integer quantity, Long id) {
    if (quantity < 0 || quantity > 99)
      throw new InvalidQuantityException("Cart item must have a quantity between 0 and 99");
    Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
    if (optionalCartItem.isEmpty()) throw new EntityNotFoundException("Cart item does not exist");
    CartItem cartItem = optionalCartItem.get();
    cartItem.setQuantity(quantity);
    return cartItemRepository.save(cartItem);
  }

  public void delete(Long id) {
    Optional<CartItem> cartItem = cartItemRepository.findById(id);
    if (cartItem.isEmpty()) throw new EntityNotFoundException();
    cartItemRepository.deleteById(id);
  }

  // TODO Patch
}

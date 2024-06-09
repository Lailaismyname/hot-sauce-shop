package com.example.sauce.cart;

import com.example.sauce.cartitem.CartItem;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
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

  public Cart addItemToCart(Long cartId, CartItem cartItem) {
    Optional<Cart> cartObject = cartRepository.findById(cartId);
    if (cartObject.isEmpty())
      throw new EntityNotFoundException("Cart of id " + cartId + " can not be found");
    Cart cart = cartObject.get();
    Set<CartItem> currentItemsInCart = cart.getCartItems();
    currentItemsInCart.add(cartItem);
    cart.setCartItems(currentItemsInCart);
    return cartRepository.save(cart);
  }

  public Cart deleteItemFromCart(Long cartId, Long idOfItemToDelete) {
    Optional<Cart> cartObject = cartRepository.findById(cartId);
    if (cartObject.isEmpty())
      throw new EntityNotFoundException("Cart of id " + cartId + " can not be found");
    Cart cart = cartObject.get();
    Set<CartItem> updatedCartItems =
        cart.getCartItems().stream()
            .filter((CartItem) -> !Objects.equals(CartItem.getId(), idOfItemToDelete))
            .collect(Collectors.toSet());
    cart.setCartItems(updatedCartItems);
    return cartRepository.save(cart);
  }

  public void delete(Long id) {
    Optional<Cart> cart = cartRepository.findById(id);
    if (cart.isEmpty()) throw new EntityNotFoundException();
    cartRepository.deleteById(id);
  }

  // TODO Patch
}

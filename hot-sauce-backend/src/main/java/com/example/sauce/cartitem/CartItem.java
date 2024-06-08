package com.example.sauce.cartitem;

import com.example.sauce.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class CartItem {
  public static CartItem of(Integer quantity, Item item) {
    if (quantity < 0 || quantity > 99)
      throw new InvalidQuantityException("Quantity must be a minimum of 0 and a maximum of 99");
    return new CartItem(quantity, item);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;

  @ManyToOne @Setter Item item;

  public CartItem(Integer quantity, Item item) {
    this.quantity = quantity;
    this.item = item;
  }
}

class InvalidQuantityException extends RuntimeException {
  public InvalidQuantityException(String message) {
    super(message);
  }
}

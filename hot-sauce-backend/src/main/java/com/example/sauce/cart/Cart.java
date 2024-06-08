package com.example.sauce.cart;

import com.example.sauce.cartitem.CartItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Cart {
  @Id @GeneratedValue private Long id;

  @OneToMany @Setter private Set<CartItem> cartItems = new HashSet<>();
}

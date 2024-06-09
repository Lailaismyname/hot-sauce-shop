package com.example.sauce.cart;

import com.example.sauce.cartitem.CartItem;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany @Setter private Set<CartItem> cartItems = new HashSet<>();
}

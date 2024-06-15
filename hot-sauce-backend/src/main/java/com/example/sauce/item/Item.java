package com.example.sauce.item;

import com.example.sauce.cartitem.CartItem;
import com.example.sauce.ingredient.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter private String name;
  @Setter private String description;
  @Setter private Double price;

  @OneToMany(mappedBy = "item")
  @JsonIgnore
  private Set<CartItem> cartItems = new HashSet<>();

  private SpiceLevel spiceLevel;

  @ManyToMany private Set<Ingredient> ingredients = new HashSet<>();

  public Item(String name, String description, Double price, SpiceLevel spiceLevel) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.spiceLevel = spiceLevel;
  }
}

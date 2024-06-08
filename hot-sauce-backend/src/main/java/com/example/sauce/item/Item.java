package com.example.sauce.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Item {
  @Id @GeneratedValue private Long id;

  @Setter private String name;
  @Setter private String description;
  @Setter private Double price;

  public Item(String name, String description, Double price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }
}

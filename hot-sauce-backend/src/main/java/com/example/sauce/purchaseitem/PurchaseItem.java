package com.example.sauce.purchaseitem;

import com.example.sauce.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class PurchaseItem {
  public static PurchaseItem of(Item item, Integer quantity) {
    return new PurchaseItem(item, quantity);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Setter @ManyToOne private Item item;

  @Setter private Integer quantity;

  public PurchaseItem(Item item, Integer quantity) {
    this.item = item;
    this.quantity = quantity;
  }
}

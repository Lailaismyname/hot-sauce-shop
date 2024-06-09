package com.example.sauce.purchase;

import com.example.sauce.purchaseitem.PurchaseItem;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @OneToMany private Set<PurchaseItem> purchaseItems;

  private final LocalDateTime createdOn = LocalDateTime.now();

  @Setter private Boolean isOpen;
}

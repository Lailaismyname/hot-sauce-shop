package com.example.sauce;

import com.example.sauce.cart.Cart;
import com.example.sauce.cart.CartService;
import com.example.sauce.cartitem.CartItemService;
import com.example.sauce.ingredient.IngredientService;
import com.example.sauce.item.Item;
import com.example.sauce.item.ItemService;
import com.example.sauce.item.SpiceLevel;
import com.example.sauce.purchase.PurchaseService;
import com.example.sauce.purchaseitem.PurchaseItemService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
  private final CartService cartService;
  private final ItemService itemService;
  private final CartItemService cartItemService;
  private final PurchaseItemService purchaseItemService;
  private final PurchaseService purchaseService;
  private final IngredientService ingredientService;

  public Seeder(
      CartService cartService,
      ItemService itemService,
      CartItemService cartItemService,
      PurchaseItemService purchaseItemService,
      PurchaseService purchaseService,
      IngredientService ingredientService) {
    this.cartService = cartService;
    this.itemService = itemService;
    this.cartItemService = cartItemService;
    this.purchaseItemService = purchaseItemService;
    this.purchaseService = purchaseService;
    this.ingredientService = ingredientService;
  }

  @Override
  public void run(String... args) throws Exception {
    seedIngredients();
    seedItems();
    seedCartItems();
    seedCarts();
    seedPurchaseItems();
    seedPurchases();
  }

  private void seedIngredients() {
    if (!ingredientService.getAll().isEmpty()) return;
    List<String> ingredients = new ArrayList<>(List.of("jalapeno","chili","habanero","ghost pepper","carolina reaper","scorpion pepper","chipotle","cayenne","bonnet pepper","madame jeanette","garlic","mango","pineapple","adobo spice","cilantro","harissa","lime","molasses","bourbon"));
    ingredients.forEach(ingredientService::save);
  }

  private void seedItems() {
    if (!itemService.getAll().isEmpty()) return;

    itemService.save(
        new Item(
            "Sriracha",
            "The classic spicy and tangy Sriracha sauce with a kick of garlic.",
            7.99,
            SpiceLevel.HOT));
    itemService.save(
        new Item(
            "Gochujang",
            "A Korean fermented chili paste with a sweet and spicy flavor profile.",
            9.99,
            SpiceLevel.MEDIUM));

    itemService.save(
        new Item(
            "Dragon's Breath",
            "A fiery hot sauce with a mix of ghost peppers and habaneros.",
            12.99,
            SpiceLevel.EXTRA_HOT));
    itemService.save(
        new Item(
            "Inferno Blaze",
            "A scorching blend of Carolina Reaper and scorpion peppers.",
            15.99,
            SpiceLevel.ULTRA_HOT));
    itemService.save(
        new Item(
            "Tropical Heat",
            "A sweet and spicy sauce with mango, pineapple, and habaneros.",
            10.99,
            SpiceLevel.HOT));
    itemService.save(
        new Item(
            "Smokin' Chipotle",
            "A rich and smoky sauce with chipotle peppers and adobo spices.",
            8.99,
            SpiceLevel.MEDIUM));
    itemService.save(
        new Item(
            "Garlic Fury",
            "A bold and zesty sauce with roasted garlic and cayenne peppers.",
            9.99,
            SpiceLevel.MILD));
    itemService.save(
        new Item(
            "Habanero Havoc",
            "A tangy and spicy sauce featuring fresh habanero peppers.",
            11.99,
            SpiceLevel.HOT));
    itemService.save(
        new Item(
            "Jalapeño Joy",
            "A mild and flavorful sauce with green jalapeños and cilantro.",
            7.99,
            SpiceLevel.MILD));
    itemService.save(
        new Item(
            "Ghostly Grin",
            "An intense sauce with ghost peppers and a hint of lime.",
            13.99,
            SpiceLevel.EXTRA_HOT));
    itemService.save(
        new Item(
            "Caribbean Crush",
            "A tropical sauce with scotch bonnet peppers and island spices.",
            10.99,
            SpiceLevel.HOT));
    itemService.save(
        new Item(
            "Smoky Bourbon",
            "A rich and smoky sauce with bourbon, molasses, and chipotle peppers.",
            14.99,
            SpiceLevel.ULTRA_HOT));
    itemService.save(
        new Item(
            "Laila's super spice and everything nice",
            "Very sweet and spicy hot sauce, the recipe has been a secret for age and will remain as such.",
            127.99,
            SpiceLevel.HOT));
  }

  private void seedCartItems() {
    if (!cartItemService.getAll().isEmpty()) return;

    List<Item> items = itemService.getAll();
    Random random = new Random();
    for (Item item : items) {
      cartItemService.save(random.nextInt(100), item.getId());
    }
  }

  private void seedCarts() {
    if (!cartService.getAll().isEmpty()) return;

    for (int i = 0; i < 10; i++) {
      cartService.save(new Cart());
    }
  }

  private void seedPurchaseItems() {
    if (!purchaseItemService.getAll().isEmpty()) return;

    List<Item> items = itemService.getAll();
    Random random = new Random();
    for (Item item : items) {
      purchaseItemService.save(random.nextInt(100), item.getId());
    }
  }

  private void seedPurchases() {
    if (!purchaseService.getAll().isEmpty()) return;

    //
  }
}

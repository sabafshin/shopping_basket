// author: Afshin Sabahi

import munit._
import shopping_basket._

class TestCaseClassesSuite extends FunSuite {
  test("Item should have a name and price") {
    val item = Item("Apples", 1.0)
    assertEquals(item.name, "Apples")
    assertEquals(item.price, 1.0)
  }
  test("ShoppingItem should have an item and quantity") {
    val item = Item("Apples", 1.0)
    val shoppingItem = ShoppingItem(item, 2)
    assertEquals(shoppingItem.item, item)
    assertEquals(shoppingItem.quantity, 2)
  }
  test(
    "SpecialOffer should have a target item, description, count, and discount"
  ) {
    val item = Item("Apples", 1.0)
    val specialOffer = SpecialOffer(item, "Buy one get one free", 2, 0.5)
    assertEquals(specialOffer.targetItem, item)
    assertEquals(specialOffer.description, "Buy one get one free")
    assertEquals(specialOffer.count, 2)
    assertEquals(specialOffer.discount, 0.5)
  }
}

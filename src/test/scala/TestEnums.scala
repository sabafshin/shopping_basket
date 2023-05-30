import munit._
import shopping_basket.Item
import shopping_basket.ItemStock

class TestItemStockSuite extends FunSuite {

  test("ItemStock should have the correct prices") {
    assertEquals(ItemStock.Soup.item.price, 0.65)
    assertEquals(ItemStock.Bread.item.price, 0.80)
    assertEquals(ItemStock.Milk.item.price, 1.30)
    assertEquals(ItemStock.Apples.item.price, 1.00)
  }

  test("ItemStock should have the correct names") {
    assertEquals(ItemStock.Soup.item.name, "Soup")
    assertEquals(ItemStock.Bread.item.name, "Bread")
    assertEquals(ItemStock.Milk.item.name, "Milk")
    assertEquals(ItemStock.Apples.item.name, "Apples")
  }

  test("Check that an Item can be created from an ItemStock") {
    val item = ItemStock.Soup.item
    assertEquals(item.name, "Soup")
    assertEquals(item.price, 0.65)
  }

}

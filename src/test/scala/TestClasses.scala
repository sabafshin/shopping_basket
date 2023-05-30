import munit._
import shopping_basket._

class TestShoppingBasketSuite extends FunSuite {

  test("addItem should add defined goods to the basket") {
    val basket = new ShoppingBasket
    basket.addItem("Soup", 2)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)
    basket.addItem("Apples", 3)
    assertEquals(
      basket.shoppingItems,
      List(
        ShoppingItem(Item("Apples", 1.00), 3),
        ShoppingItem(Item("Milk", 1.30), 1),
        ShoppingItem(Item("Bread", 0.80), 1),
        ShoppingItem(Item("Soup", 0.65), 2)
      )
    )
  }

  test("addItem should throw an exception if the item is not found") {
    val basket = new ShoppingBasket
    interceptMessage[IllegalArgumentException]("Item not found: Bananas") {
      basket.addItem("Bananas", 1)
    }
  }

  test("addOrUpdate should add a new item to the basket") {
    val basket = new ShoppingBasket
    basket.addOrUpdateShoppingItems(Item("Soup", 0.65), 2)
    assertEquals(
      basket.shoppingItems,
      List(ShoppingItem(Item("Soup", 0.65), 2))
    )
  }

  test("addOrUpdate should update an existing item in the basket") {
    val basket = new ShoppingBasket
    basket.addOrUpdateShoppingItems(Item("Soup", 0.65), 2)
    basket.addOrUpdateShoppingItems(Item("Soup", 0.65), 1)
    assertEquals(
      basket.shoppingItems,
      List(ShoppingItem(Item("Soup", 0.65), 3))
    )
  }

  test("applyOffers should update the count of the ApplesOffer instance") {
    val basket = new ShoppingBasket
    basket.addItem("Apples", 3)

    assertEquals(basket.offers.size, 2)

    basket.applyOffers()

    assertEquals(basket.offers.head.count, 3)
  }

  test("applyOffers should update the count of the SoupBreadOffer instance") {
    val basket = new ShoppingBasket
    basket.addItem("Soup", 4)
    basket.addItem("Bread", 1)

    assertEquals(basket.offers.size, 2)

    basket.applyOffers()

    assertEquals(basket.offers.last.count, 1)
  }

  test(
    "applyOffers should update the count of the SoupBreadOffer instance if the number of soups is odd"
  ) {
    val basket = new ShoppingBasket
    basket.addItem("Soup", 3)
    basket.addItem("Bread", 1)

    assertEquals(basket.offers.size, 2)

    basket.applyOffers()

    assertEquals(basket.offers.last.count, 1)
  }

  test(
    "applyOffers should have correct count of the offers if no shopping items have any offers"
  ) {
    val basket = new ShoppingBasket
    basket.addItem("Soup", 1)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)

    assertEquals(basket.offers.size, 2)

    basket.applyOffers()

    assertEquals(basket.offers.forall(_.count == 0), true)
  }

  test(
    "applyOffers should not add offers if the target item is not in the basket"
  ) {
    val basket = new ShoppingBasket
    basket.addItem("Soup", 1)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)

    assertEquals(basket.offers.size, 2)

    basket.applyOffers()

    assertEquals(basket.offers.forall(_.count == 0), true)
  }

  test(
    "calculateTotal should return the total price of the basket with discounts applied"
  ) {
    // Given
    val basket = new ShoppingBasket
    basket.addItem("Soup", 2)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)
    basket.addItem("Apples", 6)

    basket.applyOffers()

    // Then
    assertEquals(basket.calculateTotal(), 8.40)
  }

  test(
    "calculateTotal should return the total price of the basket if no offers are applicable"
  ) {
    // Given
    val basket = new ShoppingBasket
    basket.addItem("Soup", 1)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)

    basket.applyOffers()

    // Then
    assertEquals(basket.calculateTotal(), 2.75)
  }

  test("calculateTotal should return the total price of the basket") {
    // Given
    val basket = new ShoppingBasket
    basket.addItem("Soup", 3)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)
    basket.addItem("Apples", 6)

    basket.applyOffers()

    // Then
    assertEquals(basket.calculateTotal(), 9.05)
  }

  test("generateReceipt should return a receipt with discounts applied") {
    // Given
    val basket = new ShoppingBasket
    basket.addItem("Soup", 3)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)
    basket.addItem("Apples", 6)

    basket.applyOffers()

    // Then
    assertEquals(
      basket.generateReceipt(),
      """Subtotal: £10.05
        |Apples 10% off: 60p
        |Bread 50% off: 40p
        |Total price: £9.05""".stripMargin
    )

  }

  test("generateReceipt should return a receipt if no offers are applicable") {
    // Given
    val basket = new ShoppingBasket
    basket.addItem("Soup", 1)
    basket.addItem("Bread", 1)
    basket.addItem("Milk", 1)

    basket.applyOffers()

    // Then
    assertEquals(
      basket.generateReceipt(),
      """Subtotal: £2.75
        |(No offers available)
        |Total price: £2.75""".stripMargin
    )
  }
}

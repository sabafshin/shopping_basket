// author: Afshin Sabahi

import munit._
import shopping_basket.PriceBasket

class TestPriceBasketSpec extends FunSuite {
  def withOutputStream(test: java.io.ByteArrayOutputStream => Any): Unit = {
    val out = new java.io.ByteArrayOutputStream()
    try {
      test(out)
    } finally {
      out.close()
    }
  }

  test("PriceBasket should print the receipt for the given items") {
    withOutputStream { out =>
      Console.withOut(out) {
        PriceBasket.main(Array("Soup", "Soup", "Bread"))
      }
      val expected = """|Subtotal: £2.10
                      |Bread 50% off: 40p
                      |Total price: £1.70""".stripMargin
      assertEquals(out.toString().trim(), expected.trim())
    }
  }

  test("PriceBasket Apples Milk Bread") {
    withOutputStream { out =>
      Console.withOut(out) {
        PriceBasket.main(Array("Apples", "Milk", "Bread"))
      }
      val expected = """|Subtotal: £3.10
                        |Apples 10% off: 10p
                        |Total price: £3.00""".stripMargin
      assertEquals(out.toString().trim(), expected.trim())
    }
  }

  test("PriceBasket should throw an exception if an invalid item is passed") {
    intercept[IllegalArgumentException] {
      PriceBasket.main(Array("Apples", "Milk", "Bread", "Orange"))
    }
  }

  test("PriceBasket Milk Soup Soup") {
    withOutputStream { out =>
      Console.withOut(out) {
        PriceBasket.main(Array("Milk", "Soup", "Soup"))
      }
      val expected = """|Subtotal: £2.60
                      |(No offers available)
                      |Total price: £2.60""".stripMargin
      assertEquals(out.toString().trim(), expected.trim())
    }
  }

  test("PriceBasket no items") {
    withOutputStream { out =>
      Console.withOut(out) {
        PriceBasket.main(Array())
      }
      val expected = """|Subtotal: 0p
                        |(No offers available)
                        |Total price: 0p""".stripMargin
      assertEquals(out.toString().trim(), expected.trim())
    }
  }
}

// author: Afshin Sabahi

package shopping_basket

object PriceBasket {
  def main(args: Array[String]): Unit = {
    val basket = new ShoppingBasket
    args.foreach { item =>
      basket.addItem(item, 1)
    }
    basket.applyOffers()
    println(basket.generateReceipt())
  }
}

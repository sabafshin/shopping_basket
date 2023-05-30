// author: Afshin Sabahi

package shopping_basket

import shopping_basket.ItemStock
import shopping_basket.ShoppingItem
import shopping_basket.SpecialOffer
import shopping_basket.SpecialOffers

class ShoppingBasket {
  var shoppingItems: List[ShoppingItem] = List()
  var offers: List[SpecialOffer] = SpecialOffers.values.map(_.offer).toList

  def addItem(item: String, quantity: Int): Unit = {
    val itemStock =
      try ItemStock.values.find(_.item.name == item).get
      catch {
        case _: NoSuchElementException =>
          throw new IllegalArgumentException(s"Item not found: $item")
      }
    this.addOrUpdateShoppingItems(itemStock.item, quantity)
  }

  def addOrUpdateShoppingItems(item: Item, quantity: Int): Unit = {
    this.shoppingItems.find(_.item == item) match {
      case Some(existingItem) =>
        existingItem.quantity += quantity
      case None =>
        this.shoppingItems = ShoppingItem(item, quantity) :: shoppingItems
    }
  }

  def applyOffers(): Unit = {
    this.offers.foreach { offer =>
      offer match {
        case offer if offer.targetItem.name == "Apples" =>
          offer.count =
            try this.shoppingItems.find(_.item.name == "Apples").get.quantity
            catch { case _: NoSuchElementException => 0 }
        case offer if offer.targetItem.name == "Bread" =>
          try {
            val targetitem_quantity =
              this.shoppingItems.find(_.item.name == "Bread").get.quantity
            val soup_quantity =
              this.shoppingItems.find(_.item.name == "Soup").get.quantity

            offer.count =
              math.min(targetitem_quantity, math.floorDiv(soup_quantity, 2))

          } catch { case _: NoSuchElementException => 0 }
        case _ =>
      }
    }
  }

  def calculateTotal(): Double = {
    val total = this.shoppingItems.map { item =>
      item.item.price * item.quantity
    }.sum
    val discount = offers.map { offer =>
      offer.targetItem.price * offer.count * offer.discount
    }.sum
    total - discount
  }

  def generateReceipt(): String = {
    val sumtotal = this.shoppingItems.map { item =>
      item.item.price * item.quantity
    }.sum
    val total = this.calculateTotal()
    val discounts = this.discountsToString()
    val receipt = s"""|Subtotal: ${priceToString(sumtotal)}
                      |$discounts
                      |Total price: ${priceToString(total)}""".stripMargin
    receipt

  }

  def discountsToString(): String = {
    var discounts = ""
    this.offers.foreach { offer =>
      offer match {
        case offer if offer.count > 0 =>
          discounts += s"${offer.targetItem.name} ${(offer.discount * 100).toInt}% off: ${priceToString(
              offer.targetItem.price * offer.count * offer.discount
            )}\n"
        case _ =>
      }
    }
    if (discounts == "") discounts = "(No offers available)\n"
    discounts.strip()
  }

  def priceToString(price: Double): String = {
    price match {
      case price if price < 1     => s"${(price % 1.2f * 100).toInt}p"
      case price if price >= 1.00 => f"£$price%.2f"
    }
  }
}

// author: Afshin Sabahi

package shopping_basket

case class Item(
    var name: String,
    var price: Double
)

case class ShoppingItem(
    var item: Item,
    var quantity: Int
)

case class SpecialOffer(
    var targetItem: Item,
    var description: String,
    var count: Int = 0,
    var discount: Double
)

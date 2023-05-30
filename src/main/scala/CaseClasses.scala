package shopping_basket

case class Item(
    name: String,
    price: Double
)

case class ShoppingItem(
    item: Item,
    var quantity: Int
)

case class SpecialOffer(
    var targetItem: Item,
    description: String,
    var count: Int = 0,
    discount: Double
)

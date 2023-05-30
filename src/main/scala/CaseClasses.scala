package shopping_basket

final case class Item(
    name: String,
    price: Double
)

final case class ShoppingItem(
    item: Item,
    var quantity: Int
)

final case class SpecialOffer(
    targetItem: Item,
    description: String,
    var count: Int = 0,
    discount: Double
)

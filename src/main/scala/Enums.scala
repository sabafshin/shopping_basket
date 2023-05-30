// author: Afshin Sabahi

package shopping_basket

import shopping_basket.Item
import shopping_basket.SpecialOffer

enum ItemStock(val item: Item) {
  case Soup extends ItemStock(Item("Soup", 0.65))
  case Bread extends ItemStock(Item("Bread", 0.80))
  case Milk extends ItemStock(Item("Milk", 1.30))
  case Apples extends ItemStock(Item("Apples", 1.00))
}

enum OfferDescriptions(val description: String) {
  case ApplesOffer
      extends OfferDescriptions(
        "Apples are discounted 10% off their normal price this week"
      )
  case SoupBreadOffer
      extends OfferDescriptions(
        "Buy 2 tins of soup and get a loaf of bread for half price"
      )
}

enum SpecialOffers(var offer: SpecialOffer) {
  case ApplesOffer
      extends SpecialOffers(
        SpecialOffer(
          targetItem = ItemStock.Apples.item,
          description = OfferDescriptions.ApplesOffer.description,
          count = 0,
          discount = 0.1
        )
      )
  case SoupBreadOffer
      extends SpecialOffers(
        SpecialOffer(
          targetItem = ItemStock.Bread.item,
          description = OfferDescriptions.SoupBreadOffer.description,
          count = 0,
          discount = 0.5
        )
      )
}

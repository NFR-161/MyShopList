package com.exampleone.myshoplist.data

import com.exampleone.myshoplist.domain.ShopItem
import com.exampleone.myshoplist.domain.ShopListRepository

object ShopListRepositoryImpl {
    object ShopListRepositoryImpl : ShopListRepository {

        private val shopList = mutableListOf<ShopItem>()

        private var autoIncrementId = 0

        override fun addShopItem(shopItem: ShopItem) {
            if (shopItem.id == ShopItem.UNDEFINED_ID) {
                shopItem.id = autoIncrementId++
            }
            shopList.add(shopItem)
        }

        override fun deleteShopItem(shopItem: ShopItem) {
            shopList.remove(shopItem)
        }

        override fun editShopItem(shopItem: ShopItem) {
            val oldElement = getShopItem(shopItem.id)
            shopList.remove(oldElement)
            addShopItem(shopItem)
        }

        override fun getShopItem(shopItemId: Int): ShopItem {
            return shopList.find {
                it.id == shopItemId
            } ?: throw RuntimeException("Element with id $shopItemId not found")
        }

        override fun getShopList(): List<ShopItem> {
            return shopList.toList()
        }
    }
}
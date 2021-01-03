package dev.owsega.kazarovdelivery.data

import android.app.Application
import dev.owsega.kazarovdelivery.data.model.HeaderAdvert

class AdvertRepo(application: Application) {
    fun dummyAds(): List<HeaderAdvert> {
        return listOf(
            HeaderAdvert("Saturday Discount", "Coca-cola is a gift to any order"),
            HeaderAdvert("Tuesday Discount", "Taco Tuesday! Every order comes with tacos"),
            HeaderAdvert("Monday Discount", "Every order is free!"),
        )
    }
}

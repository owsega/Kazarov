package dev.owsega.kazarovdelivery.data.state

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import dev.owsega.kazarovdelivery.data.model.FoodItem
import dev.owsega.kazarovdelivery.data.model.HeaderAdvert

data class MainActivityState(
    val adverts: List<HeaderAdvert> = emptyList(),
    val menu: Async<Map<String, List<FoodItem>>> = Uninitialized
) : MvRxState
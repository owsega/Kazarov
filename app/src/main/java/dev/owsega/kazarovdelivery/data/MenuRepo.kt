package dev.owsega.kazarovdelivery.data

import android.app.Application
import dev.owsega.kazarovdelivery.data.model.FoodItem
import io.reactivex.Observable

class MenuRepo(application: Application) {
    fun fetchFullMenu(): Observable<Map<String, List<FoodItem>>> {
        return Observable.fromCallable {
            //todo returning empty for now
            return@fromCallable emptyMap<String, List<FoodItem>>()
        }
    }
}

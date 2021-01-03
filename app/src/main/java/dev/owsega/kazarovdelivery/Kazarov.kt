package dev.owsega.kazarovdelivery

import android.app.Application
import dev.owsega.kazarovdelivery.data.AdvertRepo
import dev.owsega.kazarovdelivery.data.MenuRepo

class Kazarov : Application() {
    val menuRepo get() = MenuRepo(this)
    val advertRepo get() = AdvertRepo(this)
}
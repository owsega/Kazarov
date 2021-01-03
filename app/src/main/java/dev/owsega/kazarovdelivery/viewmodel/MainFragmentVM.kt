package dev.owsega.kazarovdelivery.viewmodel

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import dev.owsega.kazarovdelivery.Kazarov
import dev.owsega.kazarovdelivery.data.AdvertRepo
import dev.owsega.kazarovdelivery.data.MenuRepo
import dev.owsega.kazarovdelivery.data.state.MainActivityState


class MainFragmentVM(
    initialState: MainActivityState,
    private val advertRepo: AdvertRepo,
    private val menuRepo: MenuRepo
) : BaseMvRxViewModel<MainActivityState>(initialState, debugMode = true) {

    init {
        setState { copy(adverts = advertRepo.dummyAds(), menu = Loading()) }
        menuRepo.fetchFullMenu().execute { copy(menu = it) }
    }

    companion object : MvRxViewModelFactory<MainFragmentVM, MainActivityState> {
        override fun create(viewModelContext: ViewModelContext, state: MainActivityState): MainFragmentVM {
            val advertRepo: AdvertRepo = viewModelContext.app<Kazarov>().advertRepo
            val menuRepo: MenuRepo = viewModelContext.app<Kazarov>().menuRepo
            return MainFragmentVM(state, advertRepo, menuRepo)
        }
    }
}
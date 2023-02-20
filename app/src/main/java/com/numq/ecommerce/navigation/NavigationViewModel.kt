package com.numq.ecommerce.navigation

import com.numq.ecommerce.viewmodel.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(

) : StateViewModel<NavigationState>(NavigationState()) {

    init {
        // TODO: check authentication state
        updateState { it.copy(id = "0") }
    }

}
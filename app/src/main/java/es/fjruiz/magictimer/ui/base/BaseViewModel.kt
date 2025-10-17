package es.fjruiz.magictimer.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<I: BaseIntent>: ViewModel() {

    abstract fun handleIntent(intent: I)
}
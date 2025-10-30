package es.fjruiz.magictimer.ui.base

import androidx.lifecycle.ViewModel

typealias HandleIntent<T> = (T) -> Unit

abstract class BaseViewModel<I: BaseIntent>: ViewModel() {

    abstract fun handleIntent(intent: I)
}
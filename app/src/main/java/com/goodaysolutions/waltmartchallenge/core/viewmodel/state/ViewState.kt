package com.goodaysolutions.waltmartchallenge.core.viewmodel.state

typealias ViewState = ScreenState<*, *>

data class ScreenState<R, E>(val render: R? = null, val event: E? = null)

data class Render<R>(val renderState: R? = null)

data class Event<E>(val event: E? = null, val handled: Boolean = false)
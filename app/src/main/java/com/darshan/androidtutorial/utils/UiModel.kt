package com.darshan.androidtutorial.utils

interface UiModel

open class  TransientAwareUiModel: UiModel{

    var isRedelivered: Boolean = false
}
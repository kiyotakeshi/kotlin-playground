package com.kiyotakeshi.inAction2nd.ch04

interface Clickable {
    fun click(): String

    // default implementation
    fun showOff(): String = "I'm clickable!"
}

class Button : Clickable {
    override fun click(): String = "I was clicked(button)"
}

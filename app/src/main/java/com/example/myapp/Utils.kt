package com.example.myapp.ui.main

fun String?.capitalize(): String {
    return this?.split(' ')
        ?.joinToString(" ") {
            it.replaceFirstChar(Char::uppercaseChar)
        } ?: ""
}
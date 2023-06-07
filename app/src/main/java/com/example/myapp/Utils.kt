package com.example.myapp

import android.util.Patterns

fun String?.capitalize(): String {
    return this?.split(' ')
        ?.joinToString(" ") {
            it.replaceFirstChar(Char::uppercaseChar)
        } ?: ""
}

data class ApiState<T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> loading(): ApiState<T> {
            return ApiState(Status.LOADING, null, null)
        }

        fun <T> error(message: String?): ApiState<T> {
            return ApiState(Status.ERROR, null, message)
        }

        fun <T> success(data: T?): ApiState<T> {
            return ApiState(Status.SUCCESS, data, null)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}

fun String.nameValidations() : Int? {
    return if (this.isEmpty()){
        R.string.name_empty_validate
    }else if (this.length<8){
        R.string.name_length_validate
    }else {
        null
    }
}

fun String.emailValidations() : Int? {
    return if (this.isEmpty()){
        R.string.email_empty_validate
    }else if (Patterns.EMAIL_ADDRESS.matcher(this).matches()){
        R.string.email_matcher_failed
    }else {
        null
    }
}

fun String.passwordValidations() : Int? {
    return if (this.isEmpty()){
        R.string.password_empty_validation
    }else if (this.length<8){
        R.string.password_length_validation
    }else {
        null
    }
}
package com.eg.domain.entity

class Resource<T> private constructor(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> success(data: T?, message: String = "Resource -> success"): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                message
            )
        }
        fun <T> error(message: String = "Resource -> error"): Resource<T> {
            return Resource(
                Status.ERROR,
                null,
                message
            )
        }
        fun <T> loading(): Resource<T> {
            return Resource(
                Status.LOADING,
                null,
                null
            )
        }
    }
}

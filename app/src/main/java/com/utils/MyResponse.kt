package com.utils

sealed class MyResponse<T>(val data:T?=null,val error:String?=null){
    class Loading<T>:MyResponse<T>()
    class Success<T>(data: T?):MyResponse<T>(data)
    class Error<T>(error: String?):MyResponse<T>(error = error)
}

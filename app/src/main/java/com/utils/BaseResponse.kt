package com.utils

import retrofit2.Response

class BaseResponse<T>(val response:Response<T>) {
    fun generateApi():MyResponse<T>
    {
        return when{
            response.isSuccessful->MyResponse.Success(response.body())
            response.code()==401->MyResponse.Error("")
            else->MyResponse.Error(response.message())
        }
    }
}
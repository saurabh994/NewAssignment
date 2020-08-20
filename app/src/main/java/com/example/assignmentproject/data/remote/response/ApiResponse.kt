package com.example.assignmentproject.data.remote.response

class ApiResponse<T> {
    var status: String? = ""
    var response: T? = null

    val isSuccess: Boolean?
        get() = status?.equals("ok")
}
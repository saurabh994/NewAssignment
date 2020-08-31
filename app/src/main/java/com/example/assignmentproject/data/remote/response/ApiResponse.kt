package com.example.assignmentproject.data.remote.response

class ApiResponse<T> {
    var status: String = ""
    var articles: T? = null

    val isSuccess: Boolean
        get() = status == "ok"
}
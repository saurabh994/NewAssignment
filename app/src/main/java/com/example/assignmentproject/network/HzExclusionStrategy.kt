package com.example.newproject.network

import com.example.assignmentproject.network.Exclude
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class HzExclusionStrategy : ExclusionStrategy {

    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.getAnnotation(Exclude::class.java) != null
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}

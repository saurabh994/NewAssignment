package com.example.assignmentproject.common.widget

import androidx.lifecycle.MutableLiveData

class ListLiveData<T> : MutableLiveData<MutableList<T>>() {

    init {
        value = mutableListOf()
    }

    fun get(pos: Int): T? {
        return try {
            value?.get(pos)
        } catch (e: Exception) {
            null
        }
    }

    fun add(item: T) {
        value?.apply {
            add(item)
            notify()
        }
    }

    fun add(index: Int, item: T) {
        value?.apply {
            add(index, item)
            notify()
        }
    }

    fun addAll(items: List<T>) {
        value?.apply {
            addAll(items)
            notify()
        }
    }

    fun addAll(index: Int, items: List<T>) {
        value?.apply {
            addAll(index, items)
            notify()
        }
    }

    fun remove(item: T) {
        value?.apply {
            remove(item)
            notify()
        }
    }

    fun remove(pos: Int) {
        value?.apply {
            removeAt(pos)
            notify()
        }
    }

    fun removeAll(items: List<T>) {
        value?.apply {
            removeAll(items)
            notify()
        }
    }

    fun removeAll(predicate: (T) -> Boolean) {
        value?.apply {
            removeAll(predicate)
            notify()
        }
    }

    fun update(index: Int, item: T) {
        value?.apply {
            set(index, item)
            notify()
        }
    }

    fun clear() {
        value?.apply {
            clear()
            notify()
        }
    }


    val size: Int get() = value?.size ?: 0
}
fun <T> MutableLiveData<T>.notify() {
    value = value
}


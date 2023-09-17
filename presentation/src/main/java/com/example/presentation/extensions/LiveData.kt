package com.example.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }
    })
}


fun <T> LiveData<T>.observeAsEvent(owner: LifecycleOwner, observer: Observer<in T>) {
    var previousKey: Any? = value ?: NULL
    observe(owner) { value ->
        if (previousKey == NULL || previousKey != value) {
            previousKey = value
            observer.onChanged(value)
        }
    }
}

private const val NULL = "NULL"


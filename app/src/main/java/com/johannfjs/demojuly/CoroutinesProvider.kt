package com.johannfjs.demojuly

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutinesProvider {
    companion object {
        val uiContext: CoroutineDispatcher by lazy { Dispatchers.Main }

        val bgContext: CoroutineDispatcher by lazy { Dispatchers.IO }
    }
}
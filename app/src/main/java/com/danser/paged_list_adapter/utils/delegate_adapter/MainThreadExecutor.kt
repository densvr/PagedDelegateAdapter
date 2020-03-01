package com.danser.paged_list_adapter.utils.delegate_adapter

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {
    private val handler = Handler(Looper.getMainLooper())
    override fun execute(r: Runnable) {
        handler.post(r)
    }
}

package com.goodaysolutions.waltmartchallenge.core.view

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.goodaysolutions.waltmartchallenge.R
import com.goodaysolutions.waltmartchallenge.core.view.definition.SessionManager
import com.goodaysolutions.waltmartchallenge.core.view.definition.TouchManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class CoreActivity : AppCompatActivity(),
    TouchManager, SessionManager {

    private var blockTouchEvents = false
    private var performSignOutCallback: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return !blockTouchEvents && super.dispatchTouchEvent(ev)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun blockTouchEventsTemporarily(intervalTime: Long) {
        blockTouchEvents = true
        GlobalScope.launch(Dispatchers.Main) {
            delay(intervalTime)
            blockTouchEvents = false
        }
    }

    override fun blockTouchEvents() {
        blockTouchEvents = true
    }

    override fun unlockTouchEvents() {
        blockTouchEvents = false
    }

    override fun registerCallback(
        performSignOutCallback: () -> Unit,
    ) {
        this.performSignOutCallback = performSignOutCallback
    }

    private fun performSignOut() {
        performSignOutCallback?.invoke()
    }

}

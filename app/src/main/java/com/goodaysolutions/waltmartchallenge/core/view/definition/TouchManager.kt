package com.goodaysolutions.waltmartchallenge.core.view.definition

import com.goodaysolutions.waltmartchallenge.constants.IntervalTime

interface TouchManager {

    fun blockTouchEventsTemporarily(intervalTime: Long = IntervalTime.TIME_300_MS)

    fun blockTouchEvents()

    fun unlockTouchEvents()

}
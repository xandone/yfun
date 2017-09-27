package com.app.xandone.yfun.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * author: xandone
 * created on: 2017/9/27 11:10
 */
class FloatBehavior(context: Context?, attrs: AttributeSet?) : FloatingActionButton.Behavior(context, attrs) {
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: FloatingActionButton?,
                                     directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?, child: FloatingActionButton?,
                                target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        Log.d("yandone", "oooooo");
        if (dyConsumed > 0 && child!!.visibility == View.VISIBLE) {
            child.hide()
        } else if (dyConsumed < 0 && child!!.visibility != View.VISIBLE) {
            child.show()
        }
    }
}
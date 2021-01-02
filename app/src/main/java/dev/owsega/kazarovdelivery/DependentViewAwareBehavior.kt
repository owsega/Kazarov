package dev.owsega.kazarovdelivery

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Shows FAB when a dependent view is not visible, and hides it when it's visible
 *
 * @author Gabriel Owoeye
 */
class DependentViewAwareBehavior(context: Context?, attrs: AttributeSet?) :
    FloatingActionButton.Behavior(context, attrs) {

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
        val dependentView = coordinatorLayout.findViewById<View>(R.id.header_subtitle)  // todo hardcoded ID is bad
        val scrollBounds = Rect()
        coordinatorLayout.getHitRect(scrollBounds)
        val dependentViewIsVisible = dependentView.getLocalVisibleRect(scrollBounds)
        // if (dependentViewIsVisible || dyConsumed > 0 && child.visibility == View.VISIBLE) {
        if (dependentViewIsVisible && child.visibility == View.VISIBLE) {
            child.hide(object : FloatingActionButton.OnVisibilityChangedListener() {
                override fun onHidden(fab: FloatingActionButton) {
                    super.onHidden(fab)
                    fab.visibility = View.INVISIBLE
                }
            })
        } else if (!dependentViewIsVisible && child.visibility != View.VISIBLE) {
            // || (dyConsumed < 0 && child.visibility != View.VISIBLE)) {
            child.show()
        }
    }
}

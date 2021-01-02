package dev.owsega.kazarovdelivery

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Shows FAB when a dependent view's top is at least half of the screen height, and hides it otherwise.
 *
 * Can probably be generalized at some point in the future
 * @author Gabriel Owoeye
 */
class DependentViewHalfHeightAwareBehavior(context: Context?, attrs: AttributeSet?) :
    FloatingActionButton.Behavior(context, attrs) {
    private var heightThreshold: Double = ((context?.resources?.displayMetrics?.heightPixels) ?: 1000) * 0.5

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FloatingActionButton,
        target: View,
        type: Int
    ) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
        val rect = Rect()
        val anchor = coordinatorLayout.findViewById<View>(R.id.menu)  //todo hardcoded anchor view
        anchor.getHitRect(rect)
        val shouldHide = rect.top < heightThreshold
        if (shouldHide && child.visibility == View.VISIBLE) {
            child.hide(object : FloatingActionButton.OnVisibilityChangedListener() {
                override fun onHidden(fab: FloatingActionButton) {
                    super.onHidden(fab)
                    fab.visibility = View.INVISIBLE
                }
            })
        } else if (!shouldHide && child.visibility != View.VISIBLE) {
            child.show()
        }
    }
}

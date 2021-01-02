package dev.owsega.kazarovdelivery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import dev.owsega.kazarovdelivery.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set appbar to about 70% of the screen height
        val heightDp = (resources.displayMetrics.heightPixels / 1.4).toFloat()
        val lp = binding.appbar.layoutParams as CoordinatorLayout.LayoutParams
        lp.height = heightDp.toInt()

        val dummyHeaderData = listOf(
            HeaderPageData("Saturday Discount", "Coca-cola is a gift to any order"),
            HeaderPageData("Tuesday Discount", "Coca-cola is a gift to any order"),
            HeaderPageData("Monday Discount", "Coca-cola is a gift to any order"),
        )

        binding.header.headerViewpager.adapter = HeaderPagerAdapter(dummyHeaderData, supportFragmentManager)
        binding.header.dotsIndicator.setViewPager(binding.header.headerViewpager)
        binding.header.headerViewpager.adapter?.registerDataSetObserver(binding.header.dotsIndicator.dataSetObserver)
    }

    internal class HeaderPagerAdapter(private val pages: List<HeaderPageData>, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int) = HeaderAdvertFragment.newInstance(pages[position])
        override fun getPageTitle(position: Int) = pages[position].title
        override fun getCount() = pages.size
    }

    data class HeaderPageData(val title: String, val subtitle: String)
}
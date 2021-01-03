package dev.owsega.kazarovdelivery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import dev.owsega.kazarovdelivery.data.model.HeaderAdvert
import dev.owsega.kazarovdelivery.databinding.FragmentMainBinding
import dev.owsega.kazarovdelivery.viewmodel.MainFragmentVM


class MainFragment : BaseMvRxFragment() {
    private val viewModel: MainFragmentVM by activityViewModel()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        // set appbar's height to about 70% of the screen size
        val heightDp = (resources.displayMetrics.heightPixels / 1.4).toFloat()
        val lp = binding.appbar.layoutParams as CoordinatorLayout.LayoutParams
        lp.height = heightDp.toInt()

        // setup ViewPagers
        binding.header.dotsIndicator.setViewPager(binding.header.headerViewpager)
        binding.header.headerViewpager.adapter?.registerDataSetObserver(binding.header.dotsIndicator.dataSetObserver)
        binding.menu.menuTabs.setupWithViewPager(binding.menu.menuViewpager)

        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewPagers() {
        //  todo
        val dummyMenuData = listOf("Pizza", "Sushi", "Drinks")
        binding.menu.menuViewpager.adapter = MenuPagerAdapter(dummyMenuData, childFragmentManager)
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            binding.header.headerViewpager.adapter = HeaderPagerAdapter(state.adverts, childFragmentManager)

        }
    }

    private class HeaderPagerAdapter(private val pages: List<HeaderAdvert>, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int) = HeaderAdvertFragment.newInstance(pages[position])
        override fun getPageTitle(position: Int) = pages[position].title
        override fun getCount() = pages.size
    }

    private class MenuPagerAdapter(private val pages: List<String>, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int) = MenuListFragment.newInstance(pages[position])
        override fun getPageTitle(position: Int) = pages[position]
        override fun getCount() = pages.size
    }
}
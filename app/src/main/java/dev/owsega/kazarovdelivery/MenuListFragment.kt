package dev.owsega.kazarovdelivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dev.owsega.kazarovdelivery.databinding.FragmentMenuBinding

class MenuListFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val recyclerView = _binding!!.root as RecyclerView
        recyclerView.adapter = MenuListAdapter(fetchData(arguments?.getString(TAG)))
        return recyclerView
    }

    private fun fetchData(category: String?): List<FoodItem> {
        // TODO("Not yet implemented")
        return listOf()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal class MenuListAdapter(private val data: List<FoodItem>) : RecyclerView.Adapter<VH>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(LinearLayout(parent.context))
            // TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(data[position])
        override fun getItemCount(): Int = data.size
    }

    internal class VH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(foodItem: FoodItem) {
            //  TODO("Not yet implemented")
        }
    }

    data class FoodItem(val id: Long)

    companion object {
        private const val TAG = "MenuListFragment"
        fun newInstance(category: String) = MenuListFragment().apply {
            arguments = Bundle().apply { putString(TAG, category) }
        }
    }
}


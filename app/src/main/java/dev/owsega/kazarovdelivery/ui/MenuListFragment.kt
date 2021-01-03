package dev.owsega.kazarovdelivery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.owsega.kazarovdelivery.data.model.FoodItem
import dev.owsega.kazarovdelivery.databinding.FragmentMenuBinding
import dev.owsega.kazarovdelivery.databinding.ListMenuBinding

class MenuListFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val recyclerView = _binding!!.root
        recyclerView.adapter = MenuListAdapter(fetchData(arguments?.getString(TAG)))
        return recyclerView
    }

    private fun fetchData(category: String?): List<FoodItem> {
        // TODO("Dummy Data")
        return listOf(
            FoodItem(10111, "https://picsum.photos/200"),
            FoodItem(10222202, "https://picsum.photos/200"),
            FoodItem(10111L, "https://picsum.photos/200")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal class MenuListAdapter(private val data: List<FoodItem>) : RecyclerView.Adapter<VH>() {
        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(data[position])
        override fun getItemCount(): Int = data.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VH(ListMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    internal class VH(private val binding: ListMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foodItem: FoodItem) {
            Glide.with(binding.foodImage)
                .load(foodItem.image)
                // .apply(RoundedTopCornersOnlyTransform)
                .into(binding.foodImage)
        }
    }

    companion object {
        private const val TAG = "MenuListFragment"
        fun newInstance(category: String) = MenuListFragment().apply {
            arguments = Bundle().apply { putString(TAG, category) }
        }
    }
}


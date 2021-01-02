package dev.owsega.kazarovdelivery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.owsega.kazarovdelivery.databinding.FragmentHeaderBinding

class HeaderAdvertFragment : Fragment() {
    private var _binding: FragmentHeaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHeaderBinding.inflate(inflater, container, false)
        binding.headerVpItemTitle.text = arguments?.getString(Intent.EXTRA_TITLE)
        binding.headerVpItemSubtitle.text = arguments?.getString(Intent.EXTRA_TEXT)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "HeaderAdvertFragment"
        fun newInstance(data: MainActivity.HeaderPageData) = HeaderAdvertFragment().apply {
            arguments = Bundle().apply {
                putString(Intent.EXTRA_TITLE, data.title)
                putString(Intent.EXTRA_TEXT, data.subtitle)
            }
        }
    }
}
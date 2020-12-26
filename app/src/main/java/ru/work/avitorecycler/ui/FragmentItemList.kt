package ru.work.avitorecycler.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ru.work.avitorecycler.ItemsAdapter
import ru.work.avitorecycler.viewmodel.ItemsViewModel
import ru.work.avitorecycler.R
import ru.work.avitorecycler.data.Item
import ru.work.avitorecycler.databinding.FragmentItemListBinding

class FragmentItemList : Fragment(R.layout.fragment_item_list), Observer<List<Item>> {
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: ItemsAdapter
    lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.itemsList.observe(this.viewLifecycleOwner, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ItemsAdapter(clickListenerItem)
        binding.rvItemList.adapter = adapter
        val manager =
            GridLayoutManager(requireContext(), resources.getInteger(R.integer.grid_count))
        binding.rvItemList.layoutManager = manager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val clickListenerItem = object : ItemsAdapter.OnRecyclerDeleteClick {
        override fun onClick(num: Int) {
            viewModel.removeItem(num)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentItemList()
    }

    override fun onChanged(t: List<Item>?) {
        adapter.submitList(viewModel.itemsList.value)
    }
}
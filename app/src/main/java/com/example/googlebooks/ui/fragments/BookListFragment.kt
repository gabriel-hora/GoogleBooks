package com.example.googlebooks.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlebooks.R
import com.example.googlebooks.databinding.FragmentBookListBinding
import com.example.googlebooks.model.Volume
import com.example.googlebooks.ui.BookDetailActivity
import com.example.googlebooks.ui.adapter.BookListAdapter
import com.example.googlebooks.ui.viewModel.BookListViewModel

class BookListFragment: Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this)[BookListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is BookListViewModel.State.Loading -> {
                    //Não está resgatando o atributo correto
                    binding.vwLoading.visibility = View.VISIBLE
                }
                is BookListViewModel.State.Loaded -> {
                    binding.vwLoading.visibility = View.GONE
                    binding.recyclerView.adapter = BookListAdapter(state.items, requireContext(), this::openBookDetail)
                }
                is BookListViewModel.State.Error -> {
                    binding.vwLoading.visibility = View.GONE
                    if(!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        viewModel.loadBooks()

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.search(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun openBookDetail(volume: Volume) {
        val detailIntent = Intent(requireContext(), BookDetailActivity::class.java)
        detailIntent.putExtra("book", volume)
        startActivity(detailIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
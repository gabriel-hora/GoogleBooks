package com.example.googlebooks.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlebooks.databinding.FragmentBookListBinding
import com.example.googlebooks.model.Volume
import com.example.googlebooks.repository.BookRepository
import com.example.googlebooks.ui.BookDetailActivity
import com.example.googlebooks.ui.adapter.BookListAdapter
import com.example.googlebooks.ui.viewModel.BookFavoritesViewModel
import com.example.googlebooks.ui.viewModel.BookViewModelFactory

class BookFavoritesFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookFavoritesViewModel by lazy {
        ViewModelProvider(
            this,
            BookViewModelFactory(
                BookRepository(
                    requireContext()
                )
            )
        )[BookFavoritesViewModel::class.java]
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
        binding.searchView.visibility = View.GONE
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favoriteBooks.observe(viewLifecycleOwner, Observer { items ->
                    binding.vwLoading.visibility = View.GONE
                    binding.recyclerView.adapter =
                        BookListAdapter(items, requireContext(), this::openBookDetail)
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
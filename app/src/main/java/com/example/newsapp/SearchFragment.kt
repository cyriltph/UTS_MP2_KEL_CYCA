package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.viewmodel.NewsViewModel

class SearchFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsAdapter = NewsAdapter()
        recyclerView.adapter = newsAdapter

        viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.submitList(articles)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.fetchNews(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: implement live search as user types
                return false
            }
        })

        return view
    }
}

package com.kshitiz.samachar24.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kshitiz.samachar24.databinding.FragmentNepaliFeedBinding
import com.kshitiz.samachar24.ui.adapter.FeedAdapter
import com.kshitiz.samachar24.ui.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFrag : Fragment() {
    val binding: FragmentNepaliFeedBinding by lazy {
        FragmentNepaliFeedBinding.inflate(layoutInflater)
    }
    val adapter: FeedAdapter by lazy { FeedAdapter() }
    private val mainVM: MainVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding.recyclerView.adapter = adapter
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            mainVM.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        lifecycleScope.launch {
            mainVM.articles.collectLatest {
                adapter.submitList(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

    }
}

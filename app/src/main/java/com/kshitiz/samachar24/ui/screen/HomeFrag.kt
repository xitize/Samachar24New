package com.kshitiz.samachar24.ui.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.kshitiz.samachar24.databinding.HomeFragBinding
import com.kshitiz.samachar24.ui.adapter.FeedAdapter
import com.kshitiz.samachar24.ui.state.UiState
import com.kshitiz.samachar24.ui.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFrag : Fragment() {
    val binding: HomeFragBinding by lazy {
        HomeFragBinding.inflate(layoutInflater)
    }
    lateinit var adapter: FeedAdapter
    private val mainVM: MainVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        adapter = FeedAdapter(onItemClicked = {
            val intent = Intent(requireActivity(), DetailFeed::class.java)
            intent.putExtra("NEWS", Gson().toJson(it))
            startActivity(intent)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.srlHome.isRefreshing = true
        mainVM.refresh()

        lifecycleScope.launch {
            mainVM.news.collectLatest {
                when (it) {
                    is UiState.Error -> {}
                    UiState.Loading -> {
                        binding.srlHome.isRefreshing = true
                    }

                    is UiState.Success -> {
                        adapter.submitList(it.data)
                        binding.recyclerView.scrollToPosition(0)
                        binding.srlHome.isRefreshing = false
                    }
                }
            }
        }

        binding.srlHome.setOnRefreshListener {
            mainVM.refresh()
            binding.srlHome.isRefreshing = false
        }

    }
}

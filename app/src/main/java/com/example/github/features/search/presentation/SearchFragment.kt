package com.example.github.features.search.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.github.R
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.features.search.domain.model.Git
import com.example.github.features.search.presentation.adapter.SearchAdapter
import com.example.github.utils.KeyUtils.Companion.PHOTO_KEY
import com.example.github.utils.KeyUtils.Companion.POSITION_KEY
import com.example.github.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment: Fragment(R.layout.fragment_search) {

    private val viewBinding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()
    private var position = -1
    lateinit var git: Git


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            viewModel.getSavedInstanceState(
                savedPosition = savedInstanceState.getInt(POSITION_KEY),
            )
        }
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        initViews(manager)
        observe(manager)
    }

    private fun observe(manager: LinearLayoutManager) {
        viewModel.loading.observe(viewLifecycleOwner){
            viewBinding.pbProgress.isVisible = it
        }
        viewModel.gitUser.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()}
                is ViewState.Error -> {Toast.makeText(requireContext(), "ErrorUser", Toast.LENGTH_SHORT).show()}
                is ViewState.Show -> {
                    git = it.data
                    viewBinding.rvGit.adapter = SearchAdapter(git){ url ->
                        val intent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                }
            }
        }
        viewModel.gitRepository.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {}
                is ViewState.Error -> {}
                is ViewState.Show -> {
                    git = it.data
                    viewBinding.rvGit.adapter = SearchAdapter(git){

                    }
                }
            }
        }
        viewModel.getSavedPosition.observe(viewLifecycleOwner) {
            position = it
            manager.scrollToPosition(it)
        }
    }

    private fun initViews(manager: LinearLayoutManager) {
        viewBinding.rvGit.layoutManager = manager
        viewBinding.rvGit.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                position = manager.findFirstCompletelyVisibleItemPosition()
            }
        })
        viewBinding.etSearch.doAfterTextChanged {
            if (it != null) {
                with(viewBinding.btnSearch){
                    isEnabled = it.length >= 3
                }
            }
        }
        viewBinding.btnSearch.setOnClickListener {
            viewModel.getData(viewBinding.etSearch.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(POSITION_KEY, position)
    }
}
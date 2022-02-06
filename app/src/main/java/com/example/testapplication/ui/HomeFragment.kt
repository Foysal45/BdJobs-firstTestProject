package com.example.testapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentHomeBinding
import com.example.testapplication.utils.ViewState
import com.example.testapplication.viewmodel.JobListViewModel
import org.koin.android.ext.android.inject
import timber.log.Timber

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var dataAdapter: JobListAdapter = JobListAdapter()
    private val viewModel: JobListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Job List"
        return FragmentHomeBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initView()
        getTotalJobList()
        initClickLister()
    }

    private fun initView() {
        binding?.jobListRV?.let { view ->
            with(view) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = dataAdapter
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner){ state ->
            when (state) {
                is ViewState.ShowMessage -> {
                    //requireContext().toast(state.message)
                }
                is ViewState.KeyboardState -> {
                    //hideKeyboard()
                }
                is ViewState.ProgressState -> {
                    if (state.isShow) {
                        binding?.progressBar?.visibility = View.VISIBLE
                    } else {
                        binding?.progressBar?.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun getTotalJobList() {
        binding?.swipeRefresh?.isRefreshing = false
        viewModel.getTotalJobList().observe(viewLifecycleOwner) { list ->
            val dataList=list.size
            Timber.d("size: $dataList")
            Toast.makeText(activity, "TotalJob=$dataList",Toast.LENGTH_SHORT).show()
            binding?.swipeRefresh?.isRefreshing = false
            dataAdapter.initLoad(list)
            Timber.d("response:${dataAdapter.initLoad(list)}")
            binding?.emptyView?.isVisible = list.isEmpty()
        }
    }


    private fun initClickLister() {
        binding?.swipeRefresh?.setOnRefreshListener {
            getTotalJobList()
        }
        dataAdapter.onItemClick = { model, _ ->
            val bundle = bundleOf("jobDetails" to model)
            findNavController().navigate(R.id.jobDetailsFragment,bundle)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
package com.example.testapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testapplication.R
import com.example.testapplication.api.model.Data
import com.example.testapplication.databinding.FragmentJobDetailsBinding
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class JobDetailsFragment : Fragment() {

    private var binding: FragmentJobDetailsBinding? = null
    private lateinit var model: Data
    private val ourFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
    private val givenFormat = SimpleDateFormat("MM/dd/yyyy",Locale.US)
    private val options = RequestOptions()
        .placeholder(R.drawable.ic_person_circle)
        .error(R.drawable.ic_person_circle)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Job Details "
        // Inflate the layout for this fragment
        return FragmentJobDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fetchJobDetails()
    }

    private fun fetchJobDetails() {
        model = arguments?.getParcelable("jobDetails")!!
        Timber.d("fetchJobDetails: Deadline: ${model.deadline}")
        val parsedDate = givenFormat.parse(model.deadline)
        val formattedDeadline = ourFormat.format(parsedDate)

        Timber.d("fetchJobDetails: Deadline F: $formattedDeadline")
        binding?.deadLineDetails?.text = formattedDeadline

        binding?.jobTitleDetails?.text = model.jobTitle
        binding?.companyNameDetails?.text = model.jobDetails.CompanyName
        binding?.email?.text = HtmlCompat.fromHtml(model.jobDetails.ApplyInstruction, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding?.salaryDetails?.text = "${model.maxSalary} - ${model.minSalary}"

        Glide.with(binding!!.imageDetails)
            .load(model.logo)
            .apply(options)
            .into(binding!!.imageDetails)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
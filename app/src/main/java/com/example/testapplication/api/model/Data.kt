package com.example.testapplication.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Data(
    @SerializedName("IsFeatured")
    val IsFeatured: Boolean = false,
    @SerializedName("deadline")
    val deadline: String = "",
    @SerializedName("jobDetails")
    val jobDetails: JobDetails,
    @SerializedName("jobTitle")
    val jobTitle: String = "",
    @SerializedName("logo")
    val logo: String = "",
    @SerializedName("maxExperience")
    val maxExperience: Int = 0,
    @SerializedName("maxSalary")
    val maxSalary: String = "",
    @SerializedName("minExperience")
    val minExperience: Int = 0,
    @SerializedName("minSalary")
    val minSalary: String = "",
    @SerializedName("recruitingCompanysProfile")
    val recruitingCompanysProfile: String = ""
) : Parcelable
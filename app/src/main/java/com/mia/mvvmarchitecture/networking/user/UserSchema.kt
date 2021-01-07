package com.mia.mvvvmcarchitecture.networking.user

import com.google.gson.annotations.SerializedName

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
data class UserSchema(
    @SerializedName("display_name")
    val mUserName: String? = null,

    @SerializedName("profile_image")
    val mprofileImage: String? = null
)
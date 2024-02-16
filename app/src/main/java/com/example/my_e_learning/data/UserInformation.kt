package com.example.my_e_learning.data

import com.google.gson.annotations.SerializedName

data class UserInformation(
  @SerializedName("id")
  var id : Int?,
  @SerializedName("username")
  var username : String?,
  @SerializedName("password")
  var password : String?,
  @SerializedName("alamat")
  var alamat : String?,
  @SerializedName("no_telepon")
  var no_telepon : Int?
){
  constructor() : this(
  null,
  null,
  null,
  null,
  null,
)}

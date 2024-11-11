package com.example.budget_buddy_android.models

import android.provider.ContactsContract.CommonDataKinds.Email

data class User(
    val fullName: String,
    val email: Email,
    val password: String
)
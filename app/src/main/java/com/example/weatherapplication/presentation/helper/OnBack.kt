package com.example.weatherapplication.presentation.helper

import androidx.fragment.app.Fragment

interface OnBack {
    fun from(fragment : Fragment, to : String)
}
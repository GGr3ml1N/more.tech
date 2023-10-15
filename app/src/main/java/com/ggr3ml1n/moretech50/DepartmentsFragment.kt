package com.ggr3ml1n.moretech50

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
class DepartmentsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_departments, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DepartmentsFragment()
    }
}
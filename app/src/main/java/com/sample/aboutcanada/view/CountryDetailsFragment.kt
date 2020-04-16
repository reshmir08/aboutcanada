package com.sample.aboutcanada.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.aboutcanada.R

/**
 * This fragment shows the details of the country
 */
class CountryDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CountryDetailsFragment.
         */
        @JvmStatic
        fun newInstance(): CountryDetailsFragment {
            return CountryDetailsFragment()
        }
    }
}

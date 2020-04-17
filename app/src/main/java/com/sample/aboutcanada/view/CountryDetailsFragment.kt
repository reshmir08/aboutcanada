package com.sample.aboutcanada.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.aboutcanada.R
import com.sample.aboutcanada.di.CountryModule
import com.sample.aboutcanada.di.DaggerCountryComponent
import com.sample.aboutcanada.model.entity.CountryDetails
import com.sample.aboutcanada.model.interactor.CountryInteractor
import com.sample.aboutcanada.presenter.CountryPresenter
import kotlinx.android.synthetic.main.fragment_country_details.*
import javax.inject.Inject

/**
 * This fragment shows the details of the country
 */
class CountryDetailsFragment : Fragment(), CountryView {
    @Inject
    lateinit var countryInteractor: CountryInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerCountryComponent.builder().countryModule(CountryModule()).build().inject(this)

        CountryPresenter(this, countryInteractor).countries
    }

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

    override fun countriesReady(countryDetails: CountryDetails) {
        activity?.title = countryDetails.title
        rv_country_details.adapter =
            countryDetails.rows?.let { CountryDetailsAdapter(it, requireContext()) }
    }

    override fun showLoader() {
        // TODO Show loader
    }

    override fun hideLoader() {
        // TODO Hide loader
    }

    override fun countriesFailed() {
        // TODO Show error
    }
}

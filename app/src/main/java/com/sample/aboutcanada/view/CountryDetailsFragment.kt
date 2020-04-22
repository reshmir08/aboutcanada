package com.sample.aboutcanada.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.sample.aboutcanada.R
import com.sample.aboutcanada.di.CountryModule
import com.sample.aboutcanada.di.DaggerCountryComponent
import com.sample.aboutcanada.helper.ConnectionHelper
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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setupPullToRefreshView()
        getCountryDetails()
    }

    /**
     * Setup the Pull-To-Refresh view
     */
    private fun setupPullToRefreshView() {
        srl_recycler.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimary
            )
        )

        srl_recycler.setColorSchemeColors(Color.WHITE)

        srl_recycler.setOnRefreshListener {
            srl_recycler.isRefreshing = false
            getCountryDetails()
        }
    }

    private fun getCountryDetails() {
        if (ConnectionHelper.hasNetwork(requireContext())) {
            CountryPresenter(this, countryInteractor).getCountryDetails()
        } else {
            CountryPresenter(this, countryInteractor).getCountryDetailsOffline()
        }
    }

    private fun initializeViews() {
        rv_country_details.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                VERTICAL
            )
        )
    }

    override fun countryDetailsReady(countryDetails: CountryDetails) {
        srl_recycler.visibility = View.VISIBLE

        activity?.title = countryDetails.title
        rv_country_details.adapter =
            countryDetails.rows?.let { CountryDetailsAdapter(it, requireContext()) }
        rv_country_details.adapter?.notifyDataSetChanged()
    }

    override fun showLoader() {
        pb_country_details.visibility = View.VISIBLE
        srl_recycler.visibility = View.GONE
        tv_country_details_error.visibility = View.GONE
    }

    override fun hideLoader() {
        pb_country_details.visibility = View.GONE
    }

    override fun countryDetailsFailed() {
        rv_country_details.adapter = CountryDetailsAdapter(listOf(), requireContext())
        srl_recycler.visibility = View.VISIBLE
        pb_country_details.visibility = View.GONE
        tv_country_details_error.visibility = View.VISIBLE
        tv_country_details_error.text = getString(R.string.err_no_data)
    }

    override fun onOfflineError() {
        rv_country_details.adapter = CountryDetailsAdapter(listOf(), requireContext())
        srl_recycler.visibility = View.VISIBLE
        pb_country_details.visibility = View.GONE
        tv_country_details_error.visibility = View.VISIBLE
        tv_country_details_error.text = getString(R.string.err_no_internet_connection)
    }
}

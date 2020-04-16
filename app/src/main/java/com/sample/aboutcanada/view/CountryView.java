package com.sample.aboutcanada.view;

import com.sample.aboutcanada.base.BaseMVPView;
import com.sample.aboutcanada.model.entity.CountryDetails;

/**
 * This class represents the country view interface.
 */
public interface CountryView extends BaseMVPView {

    void countriesReady(CountryDetails countryDetails);

    void countriesFailed();
}

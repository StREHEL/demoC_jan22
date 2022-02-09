package com.afklm.offertest.config;

import java.util.Arrays;
import java.util.List;

/**
 * Application constants.
 * @author A104284
 *
 */
public final class Constants {
    /**
     * Simple regexp for ISO 3166-1 countries codes (2 ou 3 letters).
     * {@link "https://en.wikipedia.org/wiki/ISO_3166-1"}
     */
    public static final String ISO_COUNTRY_REGEX = "^[A-Z] {2,3}$";
    
    /**
     * Default country is Antartica which 3-letter ISO code is "ATA".
     * {@link "https://en.wikipedia.org/wiki/ISO_3166-2:AQ"}
     */
    public static final String DEFAULT_COUNTRY_ISO = "ATA";
    
    public static final long ADULT_AGE_YEARS = 18L;
    
    public static final List<String> FRANCE_ISO_CODES = Arrays.asList("FR", "FRA");

    private Constants() {}
}

package com.kukuruznyak.wicketapp;

import com.kukuruznyak.wicketapp.modelandview.HomePage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {
    /**
     * Constructor.
     */
    public WicketApplication() {
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public Class getHomePage() {
        return HomePage.class;
    }

}

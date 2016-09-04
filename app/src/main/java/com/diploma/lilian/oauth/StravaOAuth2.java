package com.diploma.lilian.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.utils.OAuthEncoder;
import com.github.scribejava.core.utils.Preconditions;

/**
 * Created by Lilian on 2016. 04. 27..
 */
public class StravaOAuth2 extends DefaultApi20 {

    private static final String AUTHORIZATION_URL
            = " https://www.strava.com/oauth/authorize?client_id=%s&response_type=code&redirect_uri=%s";
    private static final String ACCESS_TOKEN_URL = "https://www.strava.com/oauth/token";


    protected StravaOAuth2() {
    }

    private static class InstanceHolder {
        private static final StravaOAuth2 INSTANCE = new StravaOAuth2();
    }

    public static StravaOAuth2 instance() {
        return InstanceHolder.INSTANCE;
    }


    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        Preconditions.checkValidUrl(config.getCallback(),
                "Must provide a valid url as callback. Facebook does not support OOB");
        final StringBuilder sb = new StringBuilder(String.format(AUTHORIZATION_URL, config.getApiKey(),
                OAuthEncoder.encode(config.getCallback())));
        if (config.hasScope()) {
            sb.append('&').append(OAuthConstants.SCOPE).append('=').append(OAuthEncoder.encode(config.getScope()));
        }

        final String state = config.getState();
        if (state != null) {
            sb.append('&').append(OAuthConstants.STATE).append('=').append(OAuthEncoder.encode(state));
        }
        return sb.toString();
    }

}

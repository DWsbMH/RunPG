package com.diploma.lilian.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.utils.OAuthEncoder;
import com.github.scribejava.core.utils.Preconditions;

public class RunkeeperOAuth2 extends DefaultApi20 {

    private static final String AUTHORIZATION_URL
            = "https://runkeeper.com/apps/authorize?client_id=%s&response_type=code&redirect_uri=%s";
    private static final String ACCESS_TOKEN_URL = "https://runkeeper.com/apps/token";

    private RunkeeperOAuth2() {
    }

    private static class InstanceHolder {
        private static final RunkeeperOAuth2 INSTANCE = new RunkeeperOAuth2();
    }

    public static RunkeeperOAuth2 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback.");
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

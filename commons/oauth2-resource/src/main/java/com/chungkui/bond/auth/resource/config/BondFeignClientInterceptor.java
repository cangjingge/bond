
package com.chungkui.bond.auth.resource.config;


import feign.RequestTemplate;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;


public class BondFeignClientInterceptor extends OAuth2FeignRequestInterceptor {

	private final OAuth2ClientContext oAuth2ClientContext;

	private final AccessTokenContextRelay accessTokenContextRelay;


	public BondFeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails resource,
                                      AccessTokenContextRelay accessTokenContextRelay) {
		super(oAuth2ClientContext, resource);
		this.oAuth2ClientContext = oAuth2ClientContext;
		this.accessTokenContextRelay = accessTokenContextRelay;
	}

	@Override
	public void apply(RequestTemplate template) {
		accessTokenContextRelay.copyToken();
		if (oAuth2ClientContext != null && oAuth2ClientContext.getAccessToken() != null) {
			super.apply(template);
		}
	}

}

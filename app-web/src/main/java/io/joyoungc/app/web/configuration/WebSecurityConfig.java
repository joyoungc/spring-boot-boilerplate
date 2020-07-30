package io.joyoungc.app.web.configuration;


import io.joyoungc.app.web.configuration.oauth2.CustomOAuth2AuthorizedClientService;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/***
 * Created by Aiden Jeong on 2020.07.28
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a -> a.antMatchers("/", "/error").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(o -> o.redirectionEndpoint().baseUri("/auth/kakao"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/webjars/**");
    }

    /************************************************************
     * OAuth2 설정정보
     ************************************************************/
    private static final String KAKAO_PROVIDER_ID = "kakao";
    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/auth/{registrationId}";

    /**
     * ClientRegistrationRepository
     * Bean으로 등록되면 AutoConfiguration에서 자동으로 oauth2 Login에 적용됨.
     *
     * @return
     */
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(kakaoClientRegistration(null));
    }

    /**
     * OAuth2AuthorizedClientService
     * Bean으로 등록되면 AutoConfiguration에서 자동으로 oauth2 Login에 적용됨.
     *
     * @return
     */
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new CustomOAuth2AuthorizedClientService();
    }

    /**
     * ClientRegistration for kakao
     * @param properties
     * @return
     */
    @Bean
    public ClientRegistration kakaoClientRegistration(OAuth2ClientProperties properties) {

        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(KAKAO_PROVIDER_ID);
        OAuth2ClientProperties.Provider provider = properties.getProvider().get(KAKAO_PROVIDER_ID);
        OAuth2ClientProperties.Registration registration = properties.getRegistration().get(KAKAO_PROVIDER_ID);

        builder
                .userNameAttributeName("id")
                .clientName(KAKAO_PROVIDER_ID)
                .clientId(registration.getClientId())
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate(DEFAULT_REDIRECT_URL)
                .authorizationUri(provider.getAuthorizationUri())
                .tokenUri(provider.getTokenUri())
                .userInfoUri(provider.getUserInfoUri())
                .userInfoAuthenticationMethod(AuthenticationMethod.FORM);

        return builder.build();
    }

}

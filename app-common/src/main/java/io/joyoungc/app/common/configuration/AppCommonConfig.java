package io.joyoungc.app.common.configuration;

import io.joyoungc.app.common.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/***
 * Created by Aiden Jeong on 2020.02.20
 */
@Configuration
public class AppCommonConfig {

    public static boolean IS_PRODUCTION;
    public static String[] PROFILES;

    public AppCommonConfig(Environment environment) {
        IS_PRODUCTION = Arrays.asList(environment.getActiveProfiles()).contains(Constants.PROFILE_PRODUCTION);
        PROFILES = environment.getActiveProfiles();
    }

    /**
     * 공통 Error Message Source 설정
     *
     */
    @Bean(Constants.ERROR_MESSAGE_SOURCE)
    @ConditionalOnMissingBean(name = Constants.ERROR_MESSAGE_SOURCE)
    public MessageSource errorMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages/common/error/error");
        return messageSource;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

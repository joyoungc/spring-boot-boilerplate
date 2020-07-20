package io.joyoungc.app.common.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/***
 * Created by Aiden Jeong on 2020.07.16
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String host;

}


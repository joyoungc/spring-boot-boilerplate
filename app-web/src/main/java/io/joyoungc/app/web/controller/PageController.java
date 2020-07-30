package io.joyoungc.app.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * Created by Aiden Jeong on 2020.07.28
 */
@Slf4j
@Controller
public class PageController {

    @RequestMapping("favicon.ico")
    @ResponseBody
    void ignoreFavicon() {
    }

    @GetMapping("/mypage")
    @ResponseBody
    Object myPage(@AuthenticationPrincipal OAuth2User principal) {
        log.debug("## OAuth2User : {} ", principal);

        return principal.getAttribute("kakao_account");
    }

}

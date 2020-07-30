package io.joyoungc.app.api.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * Created by Aiden Jeong on 2020.07.28
 */
@Controller
public class CommonController {

    @RequestMapping("favicon.ico")
    @ResponseBody
    void ignoreFavicon() {
    }

    @GetMapping("/mypage")
    @ResponseBody
    Object root(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttribute("kakao_account");
    }

}

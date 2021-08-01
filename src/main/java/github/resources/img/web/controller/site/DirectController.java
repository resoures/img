package github.resources.img.web.controller.site;

import github.resources.img.check.core.Account;
import github.resources.img.check.core.AuthInfo;
import github.resources.img.check.core.WebSecurityManager;
import github.resources.img.check.core.config.CheckConfig;
import github.resources.img.check.core.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("direct")
@Slf4j
public class DirectController {

    @Autowired
    private WebSecurityManager webSecurityManager;

    @PostMapping("checkLogin")
    public String checkLogin(@RequestParam("name")String name, @RequestParam("password") String password,
                             HttpServletRequest request, HttpServletResponse response){
        try {
            AuthInfo authInfo = webSecurityManager.login(new Account(name, password));
            Cookie cookie = new Cookie("token", webSecurityManager.generateToken(authInfo.getUserId()));
            cookie.setMaxAge(30 * 60);// 设置为30min
            cookie.setPath("/");
            response.addCookie(cookie);
            log.info("user:[{}] login successfully",authInfo.getUserId());
            return "redirect:"+ CheckConfig.loginSuccessUrl;
        } catch (AuthException e) {
            e.printStackTrace();
        }
        return "redirect:"+CheckConfig.loginUrl;
    }

}
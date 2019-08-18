package com.springboot.logindemo.controller;

import com.springboot.logindemo.model.SvcResult;
import com.springboot.logindemo.model.User;
import com.springboot.logindemo.repository.UserRepository;
import com.springboot.logindemo.service.UserService;
import com.springboot.logindemo.utils.SessionUtil;
import excpetions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${signing-token}")
    private String signingToken;
    @Value("${external-apex}")
    private String externalApex;

    @RequestMapping(path="/add", method=RequestMethod.POST)
    @ResponseBody
    public String addNewUser(@RequestParam @NotEmpty String name, @RequestParam @NotEmpty String email,
                             @RequestParam @NotEmpty String password, @RequestParam(required = false) boolean support) {
        User user = userService.addNewUser(name, email, password, support);
        log.info( "Add New User Successfully : {}", user.toString());
        return "Add New User Successfully!!!";
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public String login(@RequestParam @NotEmpty  String email,
                        @RequestParam @NotEmpty String password, Model model,
                        HttpServletResponse response) {
        SvcResult<User> result = userService.login(email, password);
        if (!result.isSuccess()) {
            throw new ServiceException(result.getError());
        }
        // Add Cookie
        SessionUtil.loginUser(result.getData(), response, signingToken, externalApex);
        model.addAttribute("name", result.getData().getName());
        return "index";
    }

    @RequestMapping(path="/logout", method=RequestMethod.POST)
    public String logout(HttpServletResponse response) {
        SessionUtil.logout(externalApex, response);
        return "redirect:/";
    }

    @GetMapping(path="/all")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/" )
    public String welcome(@RequestParam(name="name", required=false, defaultValue="World") String name,
                          Model model) {
        model.addAttribute("name",name);
        return "index";
    }
}

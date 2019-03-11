package com.example.srpintbootlogindemo.Controllers;

import com.example.srpintbootlogindemo.Models.User;
import com.example.srpintbootlogindemo.Models.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/add")
    @ResponseBody
    public String addNewUser(@RequestParam String name, @RequestParam String email,
                                           @RequestParam String password, User user) {
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        log.info(user.toString() + "Saved to the repo");
        return "Saved";
    }

    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String login(@RequestParam String email,
                        @RequestParam String password, Model model) {
        List<User> users = userRepository.findByEmail(email);
        if (users == null) {
            log.warn("User is not exist");
            model.addAttribute("name", "user is not exist");
            return "index";
        } else {
            User user = users.get(0);
            if (user.getPassword().equals(password)) {
                model.addAttribute("name", user.getName());
                log.warn(user.toString()+" logged in");
            } else {
                model.addAttribute("name", "logging failed");
                log.warn(user.toString()+" failed to logged in");
            }
            return "index";
        }
    }

    @GetMapping(path="/all")
    @ResponseBody
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/" )
    public String welcome(@RequestParam(name="name", required=false, defaultValue="World") String namel) {
        return "index";
    }
}

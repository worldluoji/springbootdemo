package com.springboot.logindemo.service;

import com.springboot.logindemo.model.SvcResult;
import com.springboot.logindemo.model.User;
import com.springboot.logindemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User addNewUser(String name, String email,
                              String password, boolean support) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(name);
        user.setEmail(email);
        user.setSupport(support);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return Lists.newArrayList(users);
    }

    public SvcResult<User> login(String email, String password) {
        SvcResult<User> result = new SvcResult<>();
        List<User> users = userRepository.findByEmail(email);
        if (CollectionUtils.isEmpty(users) || users.get(0) == null) {
            result.setError("User is not exist");
        } else {
            User user = users.get(0);
            if (passwordEncoder.matches(password, user.getPassword())) {
                result.setData(user);
                result.setSuccess(true);
                log.info("Login successfully!!!");
            } else {
                result.setError("Failed to logged in...");
            }
        }
        return result;
    }
}

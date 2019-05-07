package com.techprimers.mongodb.springbootmongodbexample.resource;

import com.techprimers.mongodb.springbootmongodbexample.document.Userinfo;
import com.techprimers.mongodb.springbootmongodbexample.repository.UserInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/usersinfo")
public class UsersInfoResource {

    private UserInfoRepository userInfoRepository;

    public UsersInfoResource(UserInfoRepository userRepository) {
        this.userInfoRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Userinfo> getAll() {
        return userInfoRepository.findAll();
    }
}

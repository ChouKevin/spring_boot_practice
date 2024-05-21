package com.example.demo.platform.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Auth.Permission;
import com.example.demo.Auth.UserDetailsImp;
import com.example.demo.common.task.OpResult;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    UserMapper userMapper;

    @Autowired 
    BCryptPasswordEncoder encoder;
    
    @Value("${root.pwd}") 
    String superPWD;
    
    @Getter
    private static User ROOT;
    
    @PostConstruct
    public void init() {
        ROOT = new User(-1, "root", encoder.encode(superPWD), Permission.ROOT);
    }
    
    // for spring security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByName(username);
        if (user == null) {
            log.error("user not found: " + username);
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImp(user.id, user.name, user.password, user.permission);
    }

    public User findByName(String name) {
        if (name.equals(ROOT.name)) {
            return ROOT;
        }
        UserPo platformUser = userMapper.selectByMap(Map.of("name", name)).get(0);
        return this.convert(platformUser);
    }

    public List<OpResult> create(List<User> users) {
        List<OpResult> result = new ArrayList<>();
        for (User user : users) {
            try {
                int count = userMapper.insert(convert(user));
                result.add(
                    OpResult.builder()
                    .name(user.name)
                    .succ(count > 0)
                    .build()
                );
            } catch (Exception e) {
                log.info("create user(%s) fail".formatted(user.name), e.getCause());
                result.add(
                    OpResult.builder()
                    .name(user.name)
                    .succ(false)
                    .build()
                );
            }
        }
        return result;
    }

    private User convert(UserPo platformUser) {
        return new User(
            platformUser.id, 
            platformUser.name, 
            platformUser.pwd,
            Permission.fromCode(platformUser.permission));
    }

    private UserPo convert(User user) {
        return UserPo.builder()
            .name(user.name)
            .pwd(encoder.encode(user.password))
            .permission(user.permission.code())
            .build();
    }
}

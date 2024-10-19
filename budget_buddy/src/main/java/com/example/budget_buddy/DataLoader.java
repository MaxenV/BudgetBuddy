package com.example.budget_buddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john@example.com");
        user1.setPassword(passwordEncoder.encode("password123"));
        user1.setStatus("active");
        user1.setMessage("Hello from John!");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane@example.com");
        user2.setPassword(passwordEncoder.encode("mypassword"));
        user2.setStatus("active");
        user2.setMessage("Hello from Jane!");
        userRepository.save(user2);
    }
}

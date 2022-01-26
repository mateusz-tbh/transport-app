package io.github.mateusztbh.transportappv3.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "user";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}

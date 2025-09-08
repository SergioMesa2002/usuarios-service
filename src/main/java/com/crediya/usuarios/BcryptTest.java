package com.crediya.usuarios;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = "$2a$10$7iLQGnM08LjfOmc2Dk7VOvEs3nUwWBI808nnwXVCFJ7X9nQkeoJ7y"; // el que tienes en la BD
        String raw = "123456";

        boolean match = encoder.matches(raw, hash);
        System.out.println("¿Hace match? " + match);

        // También puedes generar un nuevo hash
        String newHash = encoder.encode(raw);
        System.out.println("Nuevo hash para 123456: " + newHash);
    }
}

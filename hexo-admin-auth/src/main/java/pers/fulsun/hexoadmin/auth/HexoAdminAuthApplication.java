package pers.fulsun.hexoadmin.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HexoAdminAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(HexoAdminAuthApplication.class, args);
        System.out.println("HexoAdminAuthApplication started");
    }
}
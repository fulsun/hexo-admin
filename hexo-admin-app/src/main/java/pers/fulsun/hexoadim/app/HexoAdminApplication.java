package pers.fulsun.hexoadim.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pers.fulsun.hexoadmin")
public class HexoAdminApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(HexoAdminApplication.class, args);
    }
}

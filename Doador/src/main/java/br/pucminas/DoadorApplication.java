package br.pucminas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(value = "br.pucminas.security")
public class DoadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoadorApplication.class, args);
    }
}

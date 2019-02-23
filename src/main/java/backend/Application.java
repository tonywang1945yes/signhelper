package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import java.io.*;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer{
    public static void main(String[] args) throws  Exception{
        SpringApplication.run(Application.class,args);
        System.out.println("启动成功！！");
        System.out.println();
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}

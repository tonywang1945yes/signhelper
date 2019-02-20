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
//        SpringApplication.run(Application.class,args);
//        System.out.println("启动成功！！");
//        System.out.println();
        try {
            File writeName = new File("src/main/resources/dic"); // 相对路径，如果没有则要建立一个新的output.txt文件
            if(!writeName.exists()){
                writeName.mkdir();
            }
            writeName=new File("src/main/resources/dic/test1.txt");
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("我会写入文件啦1\r\n"); // \r\n即为换行
                out.write("我会写入文件啦2\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}

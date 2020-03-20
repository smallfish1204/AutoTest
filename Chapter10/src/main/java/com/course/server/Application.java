package com.course.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*ComponentScan：扫描的地址，但如果是同一层级的目录，就没必要写，写了也有红波浪线(虽然执行不报错，但哥们有强迫症呀...)*/

@SpringBootApplication
@ComponentScan("com.course")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package com.shenhua.intf;

import com.shenhua.base.myMapper.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.shenhua")
@MapperScan(basePackages = "com.shenhua.base.mapper",markerInterface = MyMapper.class)
public class ShenhuaIntfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShenhuaIntfApplication.class, args);
	}

}

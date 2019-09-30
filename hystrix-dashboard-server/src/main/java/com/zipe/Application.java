package com.zipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
   *測試步驟：
   * 1.訪問http：// localhost：8030 / hystrix.stream可以查看儀表板
   * 2.在上面的輸入框填入：http：//想監控的服務：端口/hystrix.stream進行測試
   *注意：首先要先調用一下想監控的服務的API，否則將會顯示一個空的圖表。
  */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
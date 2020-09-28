package cn.com.doone.tx.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cn.com.doone.tx.cloud.tool.common.util.SpringContextUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ServletComponentScan
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = { "cn.com.doone.tx.cloud" })
@ComponentScan(basePackages = { "cn.com.doone.tx.cloud" })
@EnableJpaRepositories(basePackages = { "cn.com.doone.tx.cloud" })
@EnableSwagger2
public class WorkWechatServiceApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WorkWechatServiceApplication.class);
	}

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(WorkWechatServiceApplication.class, args);
		SpringContextUtil.setApplicationContext(app);
		System.out.println(">>>>>>>>>>>>>>>work-wechat-service启动结束<<<<<<<<<<<<<<<<<<<<<");
	}
	
	/*@ConfigurationProperties(
			prefix = "other.datasource"
	)
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}*/


}

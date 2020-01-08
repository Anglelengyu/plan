package cn.shuhan.plan;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan(basePackages = {"cn.shuhan.plan.mapper"})
@SpringBootApplication
public class PlanApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlanApplication.class, args);
    }

    /**
     * mybatisPlus分页插件
     *
     * @return
     * @comment
     * @author Harima
     * @date Nov 2, 2018
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();

        return page;
    }
}

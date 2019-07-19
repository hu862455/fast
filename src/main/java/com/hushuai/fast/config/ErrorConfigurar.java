package com.hushuai.fast.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/19
 * @Interface: ErrorConfigurar
 * @Description: 自定义异常页面
 */
@Configuration
public class ErrorConfigurar implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[3];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/index/404Page.html");
        errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/index/500Page.html");
        errorPages[2] = new ErrorPage(HttpStatus.FORBIDDEN, "/index/403Page.html");

        registry.addErrorPages(errorPages);
    }
}

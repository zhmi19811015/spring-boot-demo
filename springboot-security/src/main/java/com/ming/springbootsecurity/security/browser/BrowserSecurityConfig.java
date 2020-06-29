package com.ming.springbootsecurity.security.browser;

import com.ming.springbootsecurity.handler.MyAuthenticationFailureHandler;
import com.ming.springbootsecurity.handler.MyAuthenticationSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;


    /**
     * 使用使用 BCrypt加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    /**
//     * 核心配置
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        auth.authenticationProvider(authenticationProvider);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                // http.httpBasic() // HTTP Basic
                // 登录跳转 URL
                .loginPage("/authentication/require")
                // 处理表单登录 URL
                .loginProcessingUrl("/login")
                // 处理登录成功
                .successHandler(authenticationSucessHandler)
                // 处理登录失败
                .failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests() // 授权配置
                // 登录跳转 URL 无需认证
                .antMatchers("/authentication/require", "/login.html").permitAll()
                // 所有请求
                .anyRequest()
                // 都需要认证
                .authenticated()
                .and().csrf().disable();


//        private static final String KEY = "liuyanzhao.com";
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/js/**","/","/fonts/**","/users").permitAll() // 都可以访问
//                .antMatchers("/h2-console/**").permitAll() // 都可以访问
//                .antMatchers("/admin/**").hasRole("ADMIN") // 需要相应的角色才能访问
//                .antMatchers("/console/**").hasAnyRole("ADMIN","USER") // 需要相应的角色才能访问
//                .and()
//                .formLogin()   //基于 Form 表单登录验证
//                .loginPage("/login") //登录页面
//                .failureUrl("/login?error=true") // 登录错误页面
//                .and().logout()
//                .and().rememberMe().key(KEY) // 启用 remember me
//                .tokenValiditySeconds(1209600)//记住两周
//                .and().exceptionHandling().accessDeniedPage("/403");  // 处理异常，拒绝访问就重定向到 403 页面
//        http.csrf().ignoringAntMatchers("/h2-console/**"); // 禁用 H2 控制台的 CSRF 防护
//        http.headers().frameOptions().sameOrigin(); // 允许来自同一来源的H2 控制台的请求
    }

}
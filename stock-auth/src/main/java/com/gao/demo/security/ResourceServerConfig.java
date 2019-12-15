package com.gao.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    private final String resourceId = "oauth2-resource";

    @Autowired
    private TokenStore tokenStore;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId);
        //resources.tokenStore(tokenStore);
    }

//    @Autowired
//    private CorsFilter corsFilter;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new AuthServerConfig.CorsFilter(), ChannelProcessingFilter.class)
//            .authorizeRequests().anyRequest().authenticated()
//            .and().anonymous().disable();
        
        http
//        .addFilterBefore(new AuthServerConfig.CorsFilter(), ChannelProcessingFilter.class)
//        .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
        .logout()
        .logoutUrl("/logout") 
        .and()
        .authorizeRequests()
        .antMatchers("/register").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/hello").permitAll()
        .antMatchers("/**").authenticated();
    } 
}

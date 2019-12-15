package com.gao.demo.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    static Logger logger = LoggerFactory.getLogger(AuthServerConfig.class);
    
    @Autowired
    AuthenticationManager  authenticationManager;
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public JdbcClientDetailsService  jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    //can use the bean defined in StockAuthApplication
//    static class CorsFilter implements Filter{
//
//        @Override
//        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//                throws IOException, ServletException {
//            HttpServletRequest req = (HttpServletRequest)request;
//            HttpServletResponse res = (HttpServletResponse)response;
//            
//            res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//            res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
//            res.setHeader("Access-Control-Max-Age", "3600");
//            res.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type");
//            res.setHeader("Access-Control-Expose-Headers", "*");
//            res.setHeader("Access-Control-Allow-Credentials", "true");
//            logger.info("origin==="+req.getHeader("origin"));
//            if(req.getMethod().equalsIgnoreCase("OPTIONS")) {
//                res.setStatus(200);
//            }else {
//                chain.doFilter(req, res);
//            }
//        }
//    }
    
     //can use the CorsFilter defined in Zuul
//    @Autowired
//    private CorsFilter corsFilter;
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.addTokenEndpointAuthenticationFilter(corsFilter);
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //type1.need to add a record in table oauth_client_details
        clients.withClientDetails(jdbcClientDetailsService()); 

        //type2.will insert this to table oauth_client_details
//        clients.jdbc(dataSource)
//          .withClient("angular")
//          .secret("{noop}123456")
//          .authorizedGrantTypes("password")
//          .resourceIds("oauth2-resource")
//          .scopes("read")
//          .accessTokenValiditySeconds(600);
        
        //type3.store this in memory
//        clients.inMemory()
//            .withClient("client")
//            .secret("{noop}secret")
//            .authorizedGrantTypes("password")
//            .resourceIds("oauth2-resource")
//            .scopes("read")
//            .accessTokenValiditySeconds(600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //tokenStore will store token in table oauth_access_token
        endpoints.authenticationManager(authenticationManager)
        .userDetailsService(myUserDetailsService)
        .tokenStore(tokenStore());
    }
    
    
}

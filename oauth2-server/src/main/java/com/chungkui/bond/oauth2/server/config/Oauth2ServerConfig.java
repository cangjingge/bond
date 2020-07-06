/*
 *
 *  *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  *  <p>
 *  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *  <p>
 *  * https://www.gnu.org/licenses/lgpl.html
 *  *  <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.chungkui.bond.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import sun.security.util.SecurityConstants;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Qualifier("iUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
/*    @Autowired
    private RedisConnectionFactory redisConnectionFactory;*/

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*todo 可以搞成自定义的方式*/
        clients.jdbc(dataSource);




    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

    /**
     * 认证
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenStore(tokenStore())
              /*  .tokenEnhancer(tokenEnhancer())*/.userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager).reuseRefreshTokens(false)
        ;
    }

    /**
     * 使用redis来存储token
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        /*RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix("oauth2:token:");*/
        return new InMemoryTokenStore();
    }

    /**
     * 自定义token
     *
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(4);
            //往token中存入额外信息
            additionalInfo.put("userName", "张森");
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

}

package com.lg.datadispose.module.conf.auth;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.lg.datadispose.module.bean.dto.SecurityUserDTO;
import com.lg.datadispose.module.constant.AuthContants;

/**
 * 
* @ClassName: AuthorizationServerConfiguration
* @Description: TODO(认证授权服务端)
* @author zlg
* @date 2019年9月23日下午4:27:46
*
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataSource dataSource;
	//用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
	//authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个 AuthenticationManager 对象
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager);
		endpoints.accessTokenConverter(accessTokenConverter());
		endpoints.tokenStore(tokenStore());
		endpoints.reuseRefreshTokens(false);
	}
	//用来配置令牌端点(Token Endpoint)的安全约束.
	//使用授权服务的 /oauth/check_token 端点你需要将这个端点暴露出去，以便资源服务可以进行访问
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')");
		oauthServer.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
	}
	//用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// jdbc方式
		clients.withClientDetails(clientDetails());
	}
	//以上的配置可以选择继承AuthorizationServerConfigurerAdapter并且覆写其中的三个configure方法来进行配置
	
	/**
	 * token converter
	 * 
	 * @return
	 */
	//JWT令牌（JWT Tokens）：
	//使用JWT令牌你需要在授权服务中使用 JwtTokenStore，资源服务器也需要一个解码的Token令牌的类 JwtAccessTokenConverter，
	//JwtTokenStore依赖这个类来进行编码以及解码，因此你的授权服务以及资源服务都需要使用这个转换类。Token令牌默认是有签名的，并且资源服务需要验证这个签名，
	//因此呢，你需要使用一个对称的Key值，用来参与签名计算，这个Key值存在于授权服务以及资源服务之中。或者你可以使用非对称加密算法来对Token进行签名，
	//Public Key公布在/oauth/token_key这个URL连接中，默认的访问安全规则是"denyAll()"，即在默认的情况下它是关闭的，
	//你可以注入一个标准的 SpEL 表达式到 AuthorizationServerSecurityConfigurer 这个配置中来将它开启（例如使用"permitAll()"来开启可能比较合适，因为它是一个公共密钥）。
	//如果你要使用 JwtTokenStore，请务必把"spring-security-jwt"这个依赖加入到你的classpath中
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
			/***
			 * 重写增强token方法,用于自定义一些token返回的信息
			 */
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				// // 与登录时候放进去的UserDetail实现类一直查看link{SecurityConfiguration}
				// SecurityUserDTO securityUserDTO =
				// (SecurityUserDTO)
				// authentication.getUserAuthentication().getPrincipal();
				// /** 自定义一些token属性 ***/
				// final Map<String, Object> additionalInformation = new
				// HashMap<>(16);
				// // additionalInformation.put("userName", userName);
				// additionalInformation.put("resources",
				// securityUserDTO.getResources());
				// ((DefaultOAuth2AccessToken) accessToken)
				// .setAdditionalInformation(additionalInformation);

				SecurityUserDTO securityUserDTO = (SecurityUserDTO) authentication.getUserAuthentication()
						.getPrincipal();
				final Map<String, Object> additionalInformation = new HashMap<>(16);
				additionalInformation.put("userId", securityUserDTO.getId());
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
				return enhancedToken;
			}
		};
		// 非对称加密
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(AuthContants.JKS_FILE),
				AuthContants.JKS_PASSWORD.toCharArray());
		accessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(AuthContants.JKS_NAME));
		return accessTokenConverter;
	}

	/**
	 * 定义clientDetails存储的方式-》Jdbc的方式，注入DataSource
	 *
	 * @return
	 */
	//客户端详情（Client Details）能够在应用程序运行的时候进行更新，可以通过访问底层的存储服务（例如将客户端详情存储在一个关系数据库的表中，就可以使用 JdbcClientDetailsService）
	//或者通过 ClientDetailsManager 接口（同时你也可以实现 ClientDetailsService 接口）来进行管理
	@Bean
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}

	/**
	 * token store
	 * 
	 * @param accessTokenConverter
	 * @return
	 */
	//JwtTokenStore：这个版本的全称是 JSON Web Token（JWT），它可以把令牌相关的数据进行编码（因此对于后端服务来说，它不需要进行存储，这将是一个重大优势），
	//但是它有一个缺点，那就是撤销一个已经授权令牌将会非常困难，所以它通常用来处理一个生命周期较短的令牌以及撤销刷新令牌（refresh_token）。
	//另外一个缺点就是这个令牌占用的空间会比较大，如果你加入了比较多用户凭证信息。
	//JwtTokenStore 不会保存任何数据，但是它在转换令牌值以及授权信息方面与 DefaultTokenServices 所扮演的角色是一样的
	@Bean
	public TokenStore tokenStore() {
		TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
		return tokenStore;
	}

}

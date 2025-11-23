package com.example.engineering.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // 手動で追記
import org.springframework.web.filter.CorsFilter; //手動で追記

// @Beanを使用するときには @Configurationを記載
// 
@Configuration
public class CorsConfig {
	// Beanとは
	// @Autowired と同じでDIコンテナに定義してくれるアノテーション？

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Angularの開発サーバーを許可
        config.addAllowedOrigin("http://localhost:4200");
        
        // すべてのHTTPメソッドを許可
        config.addAllowedMethod("*");
        
        // すべてのヘッダーを許可
        config.addAllowedHeader("*");
        
        // 認証情報を含むリクエストを許可
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        
        return new CorsFilter(source);
    }
}

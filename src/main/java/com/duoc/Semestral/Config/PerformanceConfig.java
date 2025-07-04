package com.duoc.Semestral.Config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

@Configuration
@EnableCaching
@EnableJpaAuditing
public class PerformanceConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("alumnos", "cursos", "materias", "profesores");
    }
}

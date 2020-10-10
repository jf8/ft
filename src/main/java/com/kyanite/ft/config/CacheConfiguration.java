package com.kyanite.ft.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private  JHipsterProperties jHipsterProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();
        this.jHipsterProperties = jHipsterProperties;
        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.kyanite.ft.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.kyanite.ft.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.kyanite.ft.domain.User.class.getName());
            createCache(cm, com.kyanite.ft.domain.Authority.class.getName());
            createCache(cm, com.kyanite.ft.domain.User.class.getName() + ".authorities");
            createCache(cm, com.kyanite.ft.domain.LiveSharing.class.getName());
            createCache(cm, com.kyanite.ft.domain.LiveSharing.class.getName() + ".rankingData");
            createCache(cm, com.kyanite.ft.domain.RankingData.class.getName());
            createCache(cm, com.kyanite.ft.domain.DdBookDept.class.getName());
            createCache(cm, com.kyanite.ft.domain.DdBookDept.class.getName() + ".children");
            createCache(cm, com.kyanite.ft.domain.DdBookDept.class.getName() + ".ddBookPeople");
            createCache(cm, com.kyanite.ft.domain.DdBookDept.class.getName() + ".ddUsers");
            createCache(cm, com.kyanite.ft.domain.DdBookPerson.class.getName());
            createCache(cm, com.kyanite.ft.domain.DdBookPerson.class.getName() + ".ddBookDepts");
            createCache(cm, com.kyanite.ft.domain.VFtUserSignInfo.class.getName());
            createCache(cm, com.kyanite.ft.domain.DdUser.class.getName());
            createCache(cm, com.kyanite.ft.domain.DdUser.class.getName() + ".ddBookDepts");
            createCache(cm, com.kyanite.ft.domain.DdBookDept.class.getName() + ".rankingData");
            createCache(cm, com.kyanite.ft.domain.DdBookDept.class.getName() + ".persons");
            createCache(cm, com.kyanite.ft.domain.DdBookPerson.class.getName() + ".depts");
            // jhipster-needle-ehcache-add-entry
            JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();
            javax.cache.configuration.Configuration<Object, Object> tokenConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                    ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(Constants.ACCESS_TOKEN_SECONDS)))
                    .build());
            javax.cache.configuration.Configuration<Object, Object> jsapiConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                    ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(Constants.JSAPI_TICKET_CACHE_TIME)))
                    .build());
            javax.cache.configuration.Configuration<Object, Object> repeatLoginConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                    ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(Constants.REPEAT_LOGIN_CACHE_TIME)))
                    .build());

            cm.createCache(Constants.ACCESS_TOKEN,tokenConfiguration);//ACCESS_TOKEN_SECONDS
            cm.createCache(Constants.JSAPI_TICKET,jsapiConfiguration);//ACCESS_TOKEN_SECONDS
            cm.createCache(Constants.REPEAT_LOGIN,repeatLoginConfiguration);//ACCESS_TOKEN_SECONDS
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}

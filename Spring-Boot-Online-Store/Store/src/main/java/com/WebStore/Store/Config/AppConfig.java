package com.WebStore.Store.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.WebStore.Store.Service.Basket;

@Configuration
public class AppConfig {

   // @Bean
    public Basket basket() {
        return new Basket();
    }

}
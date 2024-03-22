package com.example.sbboard2.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.html.parser.Entity;

@Configuration
public class RootConfig {
    @Bean
    public ModelMapper getMapper(){
         ModelMapper modelMapper = new ModelMapper();
         modelMapper.getConfiguration()
                 .setFieldMatchingEnabled(true)
                 .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                 .setMatchingStrategy(MatchingStrategies.LOOSE);
         return modelMapper;
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em){
        return  new JPAQueryFactory(em);
    }
}

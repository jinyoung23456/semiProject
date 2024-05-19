package com.ohgiraffers.dosirak.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ohgiraffers.dosirak", annotationClass = Mapper.class)
public class MybatisConfig {
}

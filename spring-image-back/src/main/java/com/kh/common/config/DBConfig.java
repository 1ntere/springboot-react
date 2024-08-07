package com.kh.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration//SpringBoot 설정
@PropertySource("classpath:/config.properties")
public class DBConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean//스프링부트에서 객체로 존재한다는 의미
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		return new HikariConfig();
	}
	/*
	application.properties나 config.properties에 작성해야한 hikariConfig를 자바에서 작성
	
	prefix : 접두사, 시작
	spring.datasource.hikari로 시작하는 모든 정보 가져오기
	
	suffix : 접미사, 끝
	.html, .jpg 등등 확장자가 무엇인지 작성
	*/
	
	//연결에 대한 정보
	@Bean
	public DataSource dataSource(HikariConfig config) {
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	//db와 java 관련된 추가 옵션 = sql 코드, 폴더는 어디있는지
	//db와 java 컬럼명과 변수명이 일치하지 않을 때 서로 어떻게 바라보게 하는지
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		sfb.setTypeAliasesPackage("com.kh.dto");//★ 나중에 본인의 dto 패키지 명으로 변경

		//DB와 java 컬럼명과 변수명이 일치하지 않을 때 서로 어떻게 바라보게 할지 설정
		//DB : member_id, DTO : memberId
		sfb.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		return sfb.getObject();
	}
	
	@Bean//sql 작성한 select, insert, delete, update를 이용한 database 작업을 관리
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	}
	
	@Bean//트랜잭션 : commit, rollback
	//commit, rollback 과 같은 수정하거나 삭제하거나 추가했을 때 완전하게 DB에 저장하거나 되돌릴 수 있도록 도와줌
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
		//insert, delete, update, commit을 하지 않으면 완벽하게 저장이 안 된 상태에서 select를 진행하기 때문에
		//저장을 안해서 안보이는 상황이라 여기지 않고, 코드 상이나 흐름에 문제가 있다고 생각할 수 있기 때문에 commit 진행 매니저 생성
	}
}

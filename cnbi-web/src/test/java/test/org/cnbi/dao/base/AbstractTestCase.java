package test.org.cnbi.dao.base;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月12日上午11:17:18
 这是3.0以前的版本
 @ContextConfiguration(locations = "classpath:applicationContext.xml")   
 @RunWith(SpringJUnit4ClassRunner.class)   
 @Transactional  
 @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
 */

/**
 * 指定测试用例的运行器 这里是指定了Junit4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-mvc.xml" })
public abstract class AbstractTestCase {
	@Autowired
	private ApplicationContext appplicationContext; // 自动注入applicationContext，这样就可以使用appli*.getBean("beanName")

}

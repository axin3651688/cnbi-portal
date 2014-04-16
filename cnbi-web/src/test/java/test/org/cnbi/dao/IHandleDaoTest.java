package test.org.cnbi.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cnbi.entity.User;
import org.cnbi.service.IHandleService;
import org.cnbi.utils.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.org.cnbi.dao.base.AbstractTestCase;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月12日上午11:25:54
 */
public class IHandleDaoTest extends AbstractTestCase {
	
	private static  final Log logger = LogFactory.getLog(IHandleDaoTest.class);

//	@Autowired
	//private IHandleDao handlerDao;
	
	@Autowired
	private IHandleService handlerService;

	@Test
	public void test() {
		try {
			List<User> userList = (List<User>) handlerService.query("select * from sys_user", new User());
			logger.info(JsonUtil.generateJson(userList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

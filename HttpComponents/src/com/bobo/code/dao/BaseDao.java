package com.bobo.code.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao extends SqlMapClientDaoSupport {
	// 只不过@Autowired按byType自动注入,而@Resource默认按byName自动注入罢了 
	@Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;

	@PostConstruct
	// 完成sqlMapClient初始化工作
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}

}
package com.cafe24.pickmetop.commons.repository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonsDao {
	final static Logger logger = LoggerFactory.getLogger(CommonsDao.class);
	final String NS = "com.cafe24.pickmetop.commons.repository.CommonsMapper";
	@Autowired
	@Resource(name = "sqlSessionCommons")
	private SqlSessionTemplate sqlSessionFactoryCommons;
	//�湮�� ����� ��¥ üũ
	public int selectVisitantRegdate(String visitantRegdate){
		return sqlSessionFactoryCommons.selectOne(NS + ".selectVisitantRegdate", visitantRegdate);
	}
	//�ش糯¥ �湮�� ù���
	public int insertTodayVisitant(){
		return sqlSessionFactoryCommons.insert(NS + ".insertTodayVisitant");
	}
	//�湮�� ī��Ʈ
	public int updateTodayVisitant(String visitantRegdate){
		return sqlSessionFactoryCommons.update(NS + ".updateTodayVisitant", visitantRegdate);
	}
	
}

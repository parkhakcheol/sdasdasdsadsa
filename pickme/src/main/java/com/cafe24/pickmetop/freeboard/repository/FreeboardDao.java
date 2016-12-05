package com.cafe24.pickmetop.freeboard.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.pickmetop.freeboard.model.FreeboardBookmarkVo;
import com.cafe24.pickmetop.freeboard.model.FreeboardCategoryVo;
import com.cafe24.pickmetop.freeboard.model.FreeboardReplyVo;
import com.cafe24.pickmetop.freeboard.model.FreeboardVo;

@Repository
public class FreeboardDao {
	final static Logger logger = LoggerFactory.getLogger(FreeboardDao.class);
	private final String NS = "com.cafe24.pickmetop.freeboard.repository.FreeboardMapper";
	@Autowired
	@Resource(name = "sqlSessionFreeboard")
	private SqlSessionTemplate sqlSessionFactoryFreeboard;	
	
	
	//�ϸ�ũ ���� 
	public int bookmarkDelete(String freeboardCd){
		return sqlSessionFactoryFreeboard.delete(NS+".deleteBookmarkByFreeCd",freeboardCd);
	}
	
	//�ϸ�ũ ���̵�� �˻�
	public List<String> selectBookmarkById(String loginId){
		return sqlSessionFactoryFreeboard.selectList(NS+".selectBookmarkById",loginId);
	}
	//�ϸ�ũ ���
	public int bookmarkInsert(FreeboardBookmarkVo freeboardBookmarkVo){
		return sqlSessionFactoryFreeboard.insert(NS+".bookmarkInsert",freeboardBookmarkVo);
	}
	//��ۼ���
	public int updateFreeReply(FreeboardReplyVo freeboardReplyVo){
		return sqlSessionFactoryFreeboard.update(NS+".updateFreeReply",freeboardReplyVo);
	}
	
	//�Խñۼ��� 
	public int updateFreeContent(FreeboardVo freeboardVo){
		return sqlSessionFactoryFreeboard.update(NS+".updateFreeContent",freeboardVo);
	}
	//�Խñۻ���
	public int deleteContent(String deleteContent){
		return sqlSessionFactoryFreeboard.delete(NS+".deleteContent",deleteContent);
	}
	
	//��ۻ��� 
	
	public int deleteReply(String replyCd){
		return sqlSessionFactoryFreeboard.delete(NS+".deleteReply",replyCd);
	}
	//��ü����
	public int selectFreeboardListCount(String boardSearch,String freeboardCate){
		Map<String,Object> sqlMap = new HashMap<String,Object>();
		sqlMap.put("boardSearch", boardSearch);
		sqlMap.put("cate", freeboardCate);
		logger.info("cate :{}",sqlMap.get("cate"));
		logger.info("boardSearch :{}",sqlMap.get("boardSearch"));
		return sqlSessionFactoryFreeboard.selectOne(NS+".selectTotalCount",sqlMap);
	}
	//��� ��ü ����Ʈ
	public List<FreeboardReplyVo> selectReply(){
		return sqlSessionFactoryFreeboard.selectList(NS+".selectReply");
	}
	//ī�װ� ��ü����Ʈ
	public List<FreeboardCategoryVo> selectCate(){
		return sqlSessionFactoryFreeboard.selectList(NS+".selectCate");
	}
	
	//�Խñ��Է�
	public int freeboardInsert(FreeboardVo freeboardVo){
		logger.info("freeboardVo.toString() {}",freeboardVo.toString());
		return sqlSessionFactoryFreeboard.insert(NS+".freeboardInsert",freeboardVo);
	}
	
	
	//������ & �˻�
	public List<FreeboardVo> selectFreeboardList(Map map){
		return sqlSessionFactoryFreeboard.selectList(NS+".selectFreeboardList",map);
	}
	
	
	//����Է�
	public int freeboardReplyInsert(FreeboardReplyVo freeboardReplyVo){
		return sqlSessionFactoryFreeboard.insert(NS+".freeboardReplyInsert",freeboardReplyVo);
	}
}

package com.kuzuro.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kuzuro.domain.ReplyVO;

@Repository
public class ReplyDAOlmpl implements ReplyDAO{
	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.kuzuro.mappers.replyMapper";

	@Override
	public List<ReplyDAO> readReply(int bno) throws Exception {
		return sql.selectList(namespace + ".readReply", bno);
	}

	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		sql.insert(namespace + ".writeReply", vo);
		
	}

	@Override
	public ReplyVO readReplySelect(int rno) throws Exception {
		
		return sql.selectOne(namespace + ".readReplySelect", rno);
	}

	@Override
	public void replyUpate(ReplyVO vo) throws Exception {
		sql.update(namespace + ".updateReply", vo);
		
	}

	@Override
	public void replyDelete(ReplyVO vo) throws Exception {
		sql.delete(namespace + ".deleteReply", vo);
		
	}
}

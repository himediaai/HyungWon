package com.kuzuro.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.kuzuro.domain.ReplyVO;
import com.kuzuro.persistence.ReplyDAO;

@Repository
public class ReplyServicelmpl implements ReplyService{

	
	@Inject
	private ReplyDAO dao;
	
	
//	댓글 조회
	
	@Override
	public List<ReplyDAO> readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}

	
//	댓글 작성
	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		dao.writeReply(vo);
		
	}


	@Override
	public ReplyVO readReplySelect(int rno) throws Exception {
		
		return dao.readReplySelect(rno);
	}


	@Override
	public void replyUpdate(ReplyVO vo) throws Exception {
		dao.replyUpate(vo);
		
	}


	@Override
	public void replyDelete(ReplyVO vo) throws Exception {
		dao.replyDelete(vo);
		
	}
	
	
}

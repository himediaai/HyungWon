package com.kuzuro.persistence;

import java.util.List;

import com.kuzuro.domain.ReplyVO;

public interface ReplyDAO {
	
	
//	댓글 조회
	public List<ReplyDAO> readReply(int bno) throws Exception;
	
	
//	댓글 작성
	public void writeReply(ReplyVO vo) throws Exception;
	
	
//	특정 댓글 조회
	public ReplyVO readReplySelect(int rno) throws Exception;
	
	
//	댓글 수정
	public void replyUpate(ReplyVO vo) throws Exception;
	
//	댓글 삭제
	public void replyDelete(ReplyVO vo) throws Exception;
}

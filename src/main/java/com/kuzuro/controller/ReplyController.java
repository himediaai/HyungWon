package com.kuzuro.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuzuro.domain.ReplyVO;
import com.kuzuro.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	private ReplyService replyService;
	
	
	// 댓글 조회
	
	// 댓글 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(ReplyVO vo) throws Exception {
		
		replyService.writeReply(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();
	}
	
	// 댓글 수정
	
	// 댓글 삭제
	
}
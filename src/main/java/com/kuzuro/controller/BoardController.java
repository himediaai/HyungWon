package com.kuzuro.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kuzuro.domain.BoardVO;
import com.kuzuro.domain.Criteria;
import com.kuzuro.domain.PageMaker;
import com.kuzuro.domain.ReplyVO;
import com.kuzuro.domain.SearchCriteria;
import com.kuzuro.persistence.ReplyDAO;
import com.kuzuro.service.BoardService;
import com.kuzuro.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
   
   @Inject
   BoardService service;
   
   
// DAO와 Service는 분리했는데 컨트롤러는 왜 같은걸 쓰냐면 댓글은 게시물에 종속되기 때문입니다.
// 이 프로젝트가 냉장고라고 하면, 게시물은 냉장실이고 댓글은 야채칸 같은 느낌입니다.
   @Inject
   ReplyService RepService;
   
   // 글 작성 get
   @RequestMapping(value = "/write", method = RequestMethod.GET)
   public void getWrite(HttpSession session, Model model) throws Exception {
    logger.info("get write");
    
    Object loginInfo = session.getAttribute("member");

    if(loginInfo == null) {
     model.addAttribute("msg", "login_error");
    }
   }

   // 글 작성 post
   @RequestMapping(value = "/write", method = RequestMethod.POST)
   public String postWrite(BoardVO vo) throws Exception {
    logger.info("post write");
    
    service.write(vo);
    
    return "redirect:/";
   }
   
   // 글 목록
   @RequestMapping(value = "/list", method = RequestMethod.GET)
   public void list(Model model) throws Exception {
    logger.info("get list");
    
    List<BoardVO> list = service.list();
    
    model.addAttribute("list", list);
   }
   
   // 글 조회
   @RequestMapping(value = "/read", method = RequestMethod.GET)
   public void getRead(@RequestParam("bno") int bno,
		   @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
    logger.info("get read");
    
    BoardVO vo = service.read(bno);
    model.addAttribute("read", vo);
    model.addAttribute("scri", scri);
    
    List<ReplyDAO> repList = RepService.readReply(bno);
    model.addAttribute("repList", repList);
    
   }
   
   
   
   // 글 수 정
   @RequestMapping(value = "/modify", method = RequestMethod.GET)
   public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
    logger.info("get modify");
    
    BoardVO vo = service.read(bno);
    
    model.addAttribute("modify", vo);
    
   }   
   
   // 글 삭제
   @RequestMapping(value = "/delete", method = RequestMethod.GET)
   public void getDelete(@RequestParam("bno") int bno, Model model) throws Exception {
    logger.info("get delete");
      
    model.addAttribute("delete", bno);
    
   }
   
   
   
   // 글 수정  POST   
   @RequestMapping(value = "/modify", method = RequestMethod.POST)
   public String postModify(BoardVO vo) throws Exception {
    logger.info("post modify");
    
    service.update(vo);
    
    //수정 서비스 작업을 끝내면 리스트 페이지로 이동하게 설정
    return "redirect:/board/list";
    
   }

   // 글 삭제  POST
   @RequestMapping(value = "/delete", method = RequestMethod.POST)
   public String postDelete(@RequestParam("bno") int bno) throws Exception {
    logger.info("post delete");
      
    service.delete(bno);
    
    //삭제 서비스 작업을 끝내면 리스트 페이지로 이동하게 설정
    return "redirect:/board/list";
   }
   

   
   
   
   // 글 목록 + 페이징
   @RequestMapping(value = "/listPage", method = RequestMethod.GET)
   public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
   logger.info("get list page");
   
   List<BoardVO> list = service.listPage(cri);
   model.addAttribute("list", list);
   
   PageMaker pageMaker = new PageMaker();
   pageMaker.setCri(cri);
   pageMaker.setTotalCount(service.listCount());
   model.addAttribute("pageMaker", pageMaker);
   
}
   
   // 글 목록 + 페이징 + 검색
   @RequestMapping(value = "/listSearch", method = RequestMethod.GET)
   public void listPage(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
	   logger.info("get list search");
 
	   List<BoardVO> list = service.listSearch(scri);
	   model.addAttribute("list", list);
 
	   PageMaker pageMaker = new PageMaker();
	   pageMaker.setCri(scri);
	   //pageMaker.setTotalCount(service.listCount());
	   pageMaker.setTotalCount(service.countSearch(scri));
	   model.addAttribute("pageMaker", pageMaker);
}
    
   
	// 댓글 작성
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String replyWrite(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
	 logger.info("reply write");
	 
	 RepService.writeReply(vo);
	 
	 rttr.addAttribute("bno", vo.getBno());
	 rttr.addAttribute("page", scri.getPage());
	 rttr.addAttribute("perPageNum", scri.getPerPageNum());
	 rttr.addAttribute("searchType", scri.getSearchType());
	 rttr.addAttribute("keyword", scri.getKeyword());
	 
	 return "redirect:/board/read"; 
}
   
   
	// 댓글 수정 POST
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
	 logger.info("reply update");
	 
	 RepService.replyUpdate(vo);
	 
	 rttr.addAttribute("bno", vo.getBno());
	 rttr.addAttribute("page", scri.getPage());
	 rttr.addAttribute("perPageNum", scri.getPerPageNum());
	 rttr.addAttribute("searchType", scri.getSearchType());
	 rttr.addAttribute("keyword", scri.getKeyword());
	 
	 return "redirect:/board/read";
	}

	// 댓글 삭제 POST
	@RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	public String replyDelete(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
	 logger.info("reply delete");
	 
	 RepService.replyDelete(vo);
	 
	 rttr.addAttribute("bno", vo.getBno());
	 rttr.addAttribute("page", scri.getPage());
	 rttr.addAttribute("perPageNum", scri.getPerPageNum());
	 rttr.addAttribute("searchType", scri.getSearchType());
	 rttr.addAttribute("keyword", scri.getKeyword());
	 
	 return "redirect:/board/read";
	}
	
	
	// 댓글 수정 GET
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.GET)
	public void getReplyUpdate(@RequestParam("rno") int rno,
	      @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
	 logger.info("reply update");
	 
	 ReplyVO vo = null;
	 
	 vo = RepService.readReplySelect(rno);
	 
	 model.addAttribute("readReply", vo);
	 model.addAttribute("scri", scri);
	}

	// 댓글 수정 GET
	@RequestMapping(value = "/replyDelete", method = RequestMethod.GET)
	public void getReplyDelete(@RequestParam("rno") int rno,
	      @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
	 logger.info("reply delete");
	 
	 ReplyVO vo = null;
	 
	 vo = RepService.readReplySelect(rno);
	 
	 model.addAttribute("readReply", vo);
	 model.addAttribute("scri", scri);
	}
}

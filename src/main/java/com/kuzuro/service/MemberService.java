package com.kuzuro.service;

import com.kuzuro.domain.MemberVO;

public interface MemberService {
	
	
	// 회원 가입
	public void register(MemberVO vo) throws Exception;
	
	//로그인
	public MemberVO login(MemberVO vo) throws Exception;
}

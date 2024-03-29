package com.kuzuro.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kuzuro.domain.MemberVO;
import com.kuzuro.persistence.MemberDAO;

@Service
public class MemberServicelmpl implements MemberService{

	@Inject
	private MemberDAO dao;
	
	@Override
	public void register(MemberVO vo) throws Exception {
		dao.register(vo);
		
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
}

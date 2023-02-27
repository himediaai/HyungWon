package com.kuzuro.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kuzuro.domain.MemberVO;

@Service
public class MemberDAOlmpl implements MemberDAO{

	
//	마이바티스
	@Inject
	private SqlSession sql;
	

//	매퍼
	private static String namespace = "com.kuzuro.mappers.memberMapper";
	
	@Override
	public void register(MemberVO vo) throws Exception {
		sql.insert(namespace + ".register", vo);
		
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return sql.selectOne(namespace + ".login", vo);
	}
	
	
}

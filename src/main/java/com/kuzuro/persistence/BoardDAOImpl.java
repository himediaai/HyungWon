package com.kuzuro.persistence;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.kuzuro.domain.BoardVO;
import com.kuzuro.domain.Criteria;
import com.kuzuro.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

   // 마이바티스 
   @Inject
   private SqlSession sql;
   
   // 매퍼
   private static String namespace = "com.kuzuro.mappers.boardMapper";
    
   // 작성
   @Override
   public void write(BoardVO vo) throws Exception {
    sql.insert(namespace + ".write", vo);
   }
   // 조회

   @Override
   public BoardVO read(int bno) throws Exception {
    return sql.selectOne(namespace + ".read", bno);
   }

   // 수정
   @Override
   public void update(BoardVO vo) throws Exception {
    sql.update(namespace + ".update", vo);
   }

   // 삭제
   @Override
   public void delete(int bno) throws Exception {
    sql.delete(namespace + ".delete", bno);
   }

   @Override
   public List<BoardVO> list() throws Exception {
	return sql.selectList(namespace + ".list");
}

   @Override
   public List<BoardVO> listPage(Criteria cri) throws Exception {
	return sql.selectList(namespace + ".listPage", cri);
}

   @Override
   public int listCount() throws Exception {
	return sql.selectOne(namespace + ".listCount");
}

   @Override
   public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
	return sql.selectList(namespace + ".listSearch", scri);
}

   @Override
   public int countSearch(SearchCriteria scri) throws Exception {
	return sql.selectOne(namespace + ".countSearch", scri);
}
   
}

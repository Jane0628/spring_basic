package com.spring.db.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.db.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {
	
	class BoardMapper implements RowMapper<BoardVO> {
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO vo = new BoardVO(rs.getInt("board_no"),
									 rs.getString("writer"),
									 rs.getString("title"),
									 rs.getString("content"),
									 rs.getTimestamp("reg_date").toLocalDateTime());
			return vo;
		}
	}

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public void insertArticle(BoardVO vo) {
		String sql = "INSERT INTO jdbc_board "
				   + "(writer, title, content) "
				   + "VALUES(?, ?, ?)";
		template.update(sql, vo.getWriter(), vo.getTitle(), vo.getContent());
	}

	@Override
	public List<BoardVO> getArticles() {
		String sql = "SELECT * FROM jdbc_board";
		
		try {
			return template.query(sql, new BoardMapper());
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public BoardVO getArticle(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteArticle(int bno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateArticle(BoardVO vo) {
		// TODO Auto-generated method stub

	}

}

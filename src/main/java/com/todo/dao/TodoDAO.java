package com.todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todo.dbutil.DBUtil;
import com.todo.dto.TodoDTO;

public class TodoDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	// 모든 todo 정보 가져오기
	public List<TodoDTO> getTodos() {
		try {
			List<TodoDTO> list = new ArrayList<>();
			con = DBUtil.getCon();
			String sql = "select * from todo order by idx";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String content = rs.getString("content");
				String name = rs.getString("name");
				Date date = rs.getDate("regDate");
				int sequence = rs.getInt("sequence");
				String type = rs.getString("type");
				list.add(new TodoDTO(idx, content, name, sequence, type, date));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}

	// idx로 type 가져오기
	public String checkType(int idx) {
		try {
			con = DBUtil.getCon();
			String sql = "select type from todo where idx=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			String type = "";
			while (rs.next()) {
				type = rs.getString("type");
			}

			return type;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 마음에 들지않는 코드 jstl처리가 애매함.
	// type 개수 가져오기
	public int typeCount(String type) {
		try {
			con = DBUtil.getCon();
			String sql = "select count(type) from todo where type=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, type);
			rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// todo 상태 수정하기
	public int updateTypeByidx(int idx) {
		String type = checkType(idx);
		String t = "";
		if (type.equals("todo")) {
			t = "doing";
		} else if (type.equals("doing")) {
			t = "done";
		}
		try {
			con = DBUtil.getCon();
			String sql = "update todo set type=? where idx=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, t);
			ps.setInt(2, idx);
			int n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close();
		}
	}

	// 할일 등록하기(todo add)
	public int addTodo(TodoDTO todo) {
		try {
			con = DBUtil.getCon();
			String sql = "insert into todo(content,name,sequence,type) values(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, todo.getContent());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			ps.setString(4, "todo");
			int n = ps.executeUpdate();
			System.out.println("n=" + n);
			return n;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close();
		}
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}

			if (ps != null) {
				ps.close();
			}

			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

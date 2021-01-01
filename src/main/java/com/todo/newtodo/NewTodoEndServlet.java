package com.todo.newtodo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.dao.TodoDAO;
import com.todo.dto.TodoDTO;


@WebServlet("/newtodoEnd")
public class NewTodoEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); //넘어온 값 한글처리
		
		String content=req.getParameter("content");
		String name=req.getParameter("name");
		String sequence=req.getParameter("sequence");
		
		TodoDTO todo=new TodoDTO();
		todo.setContent(content);
		todo.setName(name);
		todo.setSequence(Integer.parseInt(sequence));
		
		TodoDAO dao=new TodoDAO();
		int n=dao.addTodo(todo);
		
		String url=(n>0)?"/todo/main":"/todo/newtodo";
		res.sendRedirect(url);
		
		
	}

}

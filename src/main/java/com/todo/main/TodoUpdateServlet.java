package com.todo.main;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.dao.TodoDAO;
import com.todo.dto.TodoDTO;

/**
 * Servlet implementation class TodoUpdateServlet
 */
@WebServlet("/update")
public class TodoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String data = req.getReader().lines().collect(Collectors.joining(System.lineSeparator())); //post로 넘긴 값 string으로 받기
		
		int num = data.indexOf(":");
		String val = data.substring(num + 1, data.length() - 1);

		TodoDAO dao = new TodoDAO();
		dao.updateTypeByidx(Integer.parseInt(val));

		List<TodoDTO> list = dao.getTodos();

		// 마음에 들지않는 코드 jstl처리가 애매함.
		int todo = dao.typeCount("todo");
		int doing = dao.typeCount("doing");
		int done = dao.typeCount("done");

		req.setAttribute("todo", todo);
		req.setAttribute("doing", doing);
		req.setAttribute("done", done);
		req.setAttribute("list", list);

		RequestDispatcher reqd = req.getRequestDispatcher("/main.jsp");
		reqd.forward(req, res);

	}

}

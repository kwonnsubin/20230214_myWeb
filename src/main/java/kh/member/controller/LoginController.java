package kh.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.member.model.service.MemberService;
import kh.member.model.vo.MemberVo;

import java.io.IOException;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login") // 1. login으로 수정
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login Post");
		//1. 전송받은 데이터 읽어들이기
		MemberVo vo = new MemberVo();
		vo.setId(request.getParameter("id"));
		vo.setPasswd(request.getParameter("passwd"));
//		String id = request.getParameter("id");
//		String passwd = request.getParameter("passwd");
		System.out.println(vo);
		//2. DB다녀오기
		MemberVo result = new MemberService().login(vo);
		if(result != null) {
			System.out.println("로그인성공");
			request.getSession().setAttribute("lgnss", result);
		}else {
			System.out.println("로그인실패");
		}
		
		
		// 3. 페이지이동 및 데이터전달 (셋 중 하나로 메소드 꼭 끝냄)
		// 3-1 response.sendRedirect(request.getContextPath()+"url");
		// 3-2 request.setAttribute("name1", "값");
		// 3-2 request.getRequestDispatcher("xxx.jsp").forward(request, response);
		// 3-3 out.println(값); out.flush(); out.close();
		response.sendRedirect(request.getContextPath()+"/");
	}

}
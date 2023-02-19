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
@WebServlet("/enroll") // 1. enroll으로 수정
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/enroll.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 전달받은데이터 읽어 변수에 담기
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		MemberVo vo = new MemberVo();
		vo.setEmail(email);
		vo.setId(id);
		vo.setName(name);
		vo.setPasswd(passwd);
		System.out.println("Ctrl param: " + vo);
		
		//2. DB
		int result = new MemberService().enroll(vo);
		
		if(result < 1) {
			System.out.println("회원가입실패");
		} else {
			System.out.println("회원가입성공");
			response.sendRedirect(request.getContextPath()+"/");
		}

	}
}

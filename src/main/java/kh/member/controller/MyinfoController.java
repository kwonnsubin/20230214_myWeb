package kh.member.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.member.model.service.MemberService;
import kh.member.model.vo.MemberVo;

@WebServlet("/myinfo")
/**
 * Servlet implementation class MyinfoController
 */
public class MyinfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyinfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1
		String id = null;
		if(request.getSession().getAttribute("lgnss") != null) {
			id = ((MemberVo)(request.getSession().getAttribute("lgnss"))).getId();
		}
		//2 나의 id에 해당하는 정보를 db에서 읽어오기
		if(id != null) {
			// 3. 페이지이동 및 데이터전달 (셋 중 하나로 메소드 꼭 끝냄)
			// 3-1 response.sendRedirect(request.getContextPath()+"url");
			// 3-2 request.setAttribute("name1", "값");
			// 3-2 request.getRequestDispatcher("xxx.jsp").forward(request, response);
			// 3-3 out.println(값); out.flush(); out.close();
			request.setAttribute("myinfo", new MemberService().myInfo(id));
			request.getRequestDispatcher("/WEB-INF/view/member/myinfo.jsp").forward(request, response);
		} 
		else {
			// 방법 1 : 로그인 정보가 없을 때, 많은 jsp 페이지에서 같은 코드를 작성해야하는 불편함이 있음.
			//request.getRequestDispatcher("/WEB-INF/view/member/myinfo.jsp").forward(request, response);
			
			// 방법 2 : 로그인 정보가 없을 때, 하나의 error page를 만들어 줌
			request.setAttribute("errorMsg", "로그인되지 않아 내정보를 확인할 수 없습니다.");
			request.getRequestDispatcher("/WEB-INF/view/error/errorLogin.jsp").forward(request, response);
		}
	
	}

}

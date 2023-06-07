package com.wind.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wind.web.dao.IDao;

@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/freeboard/list")
	public String list(Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("list", dao.listDao());
		return "board/list";
	}
	
	@RequestMapping("/freeboard/content_view")
	public String content_view(HttpServletRequest request, Model model) { 
		IDao dao = sqlSession.getMapper(IDao.class);
		upHit(request.getParameter("bId"));
		model.addAttribute("content_view", dao.viewDao(request.getParameter("bId")));
		return "board/content_view";
	}

	@RequestMapping("/freeboard/write_view")
	public String writeForm() {
		return "board/write_view";
	}

	@RequestMapping("/freeboard/write")
	public String write(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.writeDao(request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent"));
		return "redirect:list";
	}
	
	@RequestMapping("/freeboard/view")
	public String view() {
		
		return "board/view";
	}
	
	@RequestMapping("/freeboard/delete")
	public String delete(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.deleteDao(request.getParameter("bId"));
		return "redirect:list";
	}
	
	@RequestMapping("/freeboard/modify")
	public String modify(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.modifyDao(request.getParameter("bName"), request.getParameter("bTitle"), 
			request.getParameter("bContent"), request.getParameter("bId"));
		return "redirect:list";
	}
	
	@RequestMapping("/freeboard/reply")
	public String reply(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		replyShape(request.getParameter("bGroup"), request.getParameter("bStep"));
		dao.replyDao(
				request.getParameter("bName"), 
				request.getParameter("bTitle"), 
				request.getParameter("bContent"), 
				Integer.parseInt(request.getParameter("bGroup")), 
				Integer.parseInt(request.getParameter("bStep"))+1, 
				Integer.parseInt(request.getParameter("bIndent"))+1);
		return "redirect:list";
	}
	
	@RequestMapping("/freeboard/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("reply_view", dao.reply_viewDao(request.getParameter("bId")));		
		return "board/reply_view";
	}
	
	public void replyShape(String bGroup, String bStep) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.replyShape(bGroup, bStep);
	}
	
	public void upHit(String bId) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.upHit(bId);
	}
	
}

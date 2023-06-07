package com.wind.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wind.web.dao.SpecialtiesDao;
import com.wind.web.dao.VetsDao;
import com.wind.web.dao.VetspecialtiesDao;
import com.wind.web.dto.VetsDto;
import com.wind.web.dto.VetspecialtiesDto;

@Controller
public class VetsController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/vetslist")
	public String vetslist(Model model) {
		VetsDao vdao = sqlSession.getMapper(VetsDao.class);
		model.addAttribute("vetslist", vdao.vetslistDao());
		return "vetslist";
	}

	@RequestMapping("/vetslistall")
	public String vetslistall(Model model) {
		
		// model : 
		// vetslist - 의사정보(id, first_name, last_name)
		// vetspeslist - 의사ID 와 전공ID 맵핑정보 (vet_id, specialty_id)
		// specialtieslist - 전공정보(id, name)
		
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
		VetspecialtiesDao vsdao = sqlSession.getMapper(VetspecialtiesDao.class);
		SpecialtiesDao sdao = sqlSession.getMapper(SpecialtiesDao.class);
		model.addAttribute("vetslist", dao.vetslistDao());
		model.addAttribute("vetspeslist", vsdao.vetspeslistDao());
		model.addAttribute("specialtieslist", sdao.specialtieslistDao());
		
		return "vetslistall";
	}
	
	@RequestMapping("/vetselect_view")
	public String vetselect_view(HttpServletRequest request, Model model) { 
		SpecialtiesDao dao = sqlSession.getMapper(SpecialtiesDao.class);
		model.addAttribute("vetselect_view", dao.vetselect_viewDao());
		return "vetselect_view"; 
	}
	
	@RequestMapping("/vetselect")
	public String vetselect(HttpServletRequest request, Model model) {
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
	
		ArrayList<VetspecialtiesDto> dto;
		dto = dao.vetselectDao(Integer.parseInt(request.getParameter("specialty_id")));

		ArrayList<VetsDto> dto2 = new ArrayList<VetsDto>();
		for(int i=0;i<dto.size();i++) {
			dto2.add(dao.vetselect2Dao(dto.get(i).getVet_id()));
			System.out.println(dto.get(i).getVet_id());
			System.out.println("vets_id = "+dao.vetselect2Dao(dto.get(i).getVet_id()));
		}
		model.addAttribute("vetselect2", dto2);
		
		System.out.println("size = " + dto.get(0).getVet_id());		
		System.out.println("size = " + dto.get(1).getVet_id());		
		System.out.println(dto.getClass());
		System.out.println(dto.size());
		return "vetselect";
	}
	
	@RequestMapping("/vetselect3")
	public String vetselect3(HttpServletRequest request, Model model) {
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
		model.addAttribute("vetselect3", dao.vetselect3Dao(Integer.parseInt(request.getParameter("specialty_id"))));
		return "vetselect3";
	}
	
	@RequestMapping("/vet_add_view")
	public String vet_add_view() {
		return "vet_add_view";
	}

	@RequestMapping("/vet_add")
	public String vet_add(HttpServletRequest request) {
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
		dao.vet_addDao(request.getParameter("first_name"), request.getParameter("last_name"));
		return "redirect:vetslist";
	}
	
	@RequestMapping("/vet_add_major")
	public String vet_add_major(Model model) {
		VetsDao dao1 = sqlSession.getMapper(VetsDao.class);
		SpecialtiesDao dao2 = sqlSession.getMapper(SpecialtiesDao.class);
		model.addAttribute("vetslist", dao1.vetslistDao());
		model.addAttribute("specialtieslist", dao2.specialtieslistDao());
		return "vet_add_major";
	}
	
	@RequestMapping("/vet_add_major_mod")
	public String vet_add_major_mod(HttpServletRequest request, Model model) {
		String vet_id = request.getParameter("vet_id");
		String[] major = request.getParameterValues("major");
		del_vetspec(vet_id);

		for(int i=0;i<major.length;i++)  {
			add_vetspec(vet_id, major[i]);
		}

		return "redirect:vetslist";
	}
	
	public void del_vetspec(String vet_id) {
		VetspecialtiesDao dao = sqlSession.getMapper(VetspecialtiesDao.class);
		dao.vetspec_deleteDao(vet_id);
	}

	public void add_vetspec(String vet_id, String specialty_id) {
		VetspecialtiesDao dao = sqlSession.getMapper(VetspecialtiesDao.class);
		dao.vetspec_addDao(vet_id, specialty_id);
	}

	@ExceptionHandler
	public String handlerException(Exception e) {
		return "viewerror";
	}
	
}

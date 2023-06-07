package com.wind.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
	
	// 트랜잭션 처리
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@RequestMapping("/vetslist")
	public String vetslist(Model model) {
		VetsDao vdao = sqlSession.getMapper(VetsDao.class);
		model.addAttribute("vetslist", vdao.vetslistDao());
		return "petclinic/vetslist";
//		return "redirect:vetslistall";
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
		
		return "petclinic/vetslistall";
	}
	
	@RequestMapping("/vetselect_view")
	public String vetselect_view(HttpServletRequest request, Model model) { 
		SpecialtiesDao dao = sqlSession.getMapper(SpecialtiesDao.class);
		model.addAttribute("vetselect_view", dao.vetselect_viewDao());
		return "petclinic/vetselect_view"; 
	}
	
	@RequestMapping("/vetselect")
	public String vetselect(HttpServletRequest request, Model model) {
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
		ArrayList<VetspecialtiesDto> dto;
		dto = dao.vetselectDao(Integer.parseInt(request.getParameter("specialty_id")));

		ArrayList<VetsDto> dto2 = new ArrayList<VetsDto>();
		for(int i=0;i<dto.size();i++) {
			dto2.add(dao.vetselect2Dao(dto.get(i).getVet_id()));
		}
		model.addAttribute("vetselect2", dto2);
		
		return "petclinic/vetselect";
	}
	
	@RequestMapping("/vetselect3")
	public String vetselect3(HttpServletRequest request, Model model) {
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
		model.addAttribute("vetselect3", dao.vetselect3Dao(Integer.parseInt(request.getParameter("specialty_id"))));
		return "petclinic/vetselect3";
	}
	
	@RequestMapping("/vet_add_view")
	public String vet_add_view() {
		return "petclinic/vet_add_view";
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
		return "petclinic/vet_add_major";
	}
	
	@RequestMapping("/vet_add_major_mod")
	public String vet_add_major_mod(HttpServletRequest request, Model model) throws Exception {
		String vet_id = request.getParameter("vet_id");
		String[] major = request.getParameterValues("major");
		
		// Spring Transaction - Reference Ch.10.6
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setName("exam-tran");
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			del_vetspec(vet_id);
			for(int i=0;i<major.length;i++)  {
				add_vetspec(vet_id, major[i]);
			}
			transactionManager.commit(status);

		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
		}
		return "redirect:vetslist";
	}
	// .../vet_delete?id=12
	@RequestMapping("/vet_delete")
	public String vet_delete(HttpServletRequest request) {
		String id = request.getParameter("id");

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			del_vetspec(id);
			del_vets(id);

			transactionManager.commit(status);
		} catch (Exception e) {
			
			transactionManager.rollback(status);
			e.printStackTrace();
		} 
		return "redirect:vetslistall";
	}
	
	
	public void del_vetspec(String vet_id) {
		VetspecialtiesDao dao = sqlSession.getMapper(VetspecialtiesDao.class);
		dao.vetspec_deleteDao(vet_id);
	}

	public void add_vetspec(String vet_id, String specialty_id) {
		VetspecialtiesDao dao = sqlSession.getMapper(VetspecialtiesDao.class);
		dao.vetspec_addDao(vet_id, specialty_id);
	}

	public void del_vets(String id) {
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
		dao.vet_deleteDao(id);
	}
	
	public void add_vets(String first_name, String last_name) {
		VetsDao dao = sqlSession.getMapper(VetsDao.class);
		dao.vet_addDao(first_name, last_name);
	}
	
	@ExceptionHandler
	public String handlerException(Exception e) {
		return "viewerror";
	}
	
}

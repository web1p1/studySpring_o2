package com.wind.web.dao;

import java.util.ArrayList;

import com.wind.web.dto.VetspecialtiesDto;

public interface VetspecialtiesDao {
	
	public void vetspec_deleteDao(String id);
	public void add_vetspec(String vet_id, String specialty_id);
	public ArrayList<VetspecialtiesDto> vetspeslistDao();

}

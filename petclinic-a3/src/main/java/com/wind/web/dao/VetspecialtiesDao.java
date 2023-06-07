package com.wind.web.dao;
import java.util.ArrayList;

import com.wind.web.dto.ContentDto;
import com.wind.web.dto.SpecialtiesDto;
import com.wind.web.dto.VetsDto;
import com.wind.web.dto.VetspecialtiesDto;

public interface VetspecialtiesDao {
	
	public void vetspec_deleteDao(String id);
	public void vetspec_addDao(String vet_id, String specialty_id);

}

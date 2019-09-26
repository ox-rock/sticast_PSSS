package com.sticast.service;

import java.util.List;

import com.sticast.entity.Winner;

public interface AdministratorService {
	
	List<Winner> getWinners(Integer questionID, String answer);
}

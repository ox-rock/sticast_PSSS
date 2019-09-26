package com.sticast.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sticast.entity.Winner;
import com.sticast.repository.ForecastRepository;
import com.sticast.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired 
	ForecastRepository forecastRepository;
	
	@Transactional
	@Override
	public List<Winner> getWinners(Integer questionID, String answer) {
		return forecastRepository.getWinners(questionID, answer);		
	}
}

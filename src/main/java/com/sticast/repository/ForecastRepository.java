package com.sticast.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticast.entity.Forecast;

@Repository("ForecastRepository")
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {
	
    ArrayList<Forecast> findAllByAccountId(Integer accountID);
}

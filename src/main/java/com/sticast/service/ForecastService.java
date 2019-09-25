package com.sticast.service;

import java.util.ArrayList;
import com.sticast.entity.Forecast;

public interface ForecastService {

	public ArrayList<Forecast> getAllForecastsByAccount(Integer accountID);
	public Double calculatePayout(Forecast forecast);
	public Forecast saveForecast(Forecast forecast);
}

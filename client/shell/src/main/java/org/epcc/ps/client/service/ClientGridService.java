package org.epcc.ps.client.service;

import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.Landscape;

/**
 * @author jiahao.cao
 * Created on 20/10/2017
 */
public interface ClientGridService {
	
	/***
	 * Get landscape
	 * 
	 * */
	Landscape getLandScape(String fileSource);
}

package org.epcc.ps.client.shell.service;

import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.client.shell.exception.PPMFileException;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;

/**
 * @author jiahao.cao
 * Created on 20/10/2017
 */
public interface ConvertService {
	
	/***
	 * Convert Landscape from ASCII bitmask file
	 * 
	 * */
	Landscape convertLandscapeFromFile(String fileSource) throws ConvertException;

	void convertLandscapeWithSpeciesToPPM(String fileName, Landscape landscape, Species species) throws PPMFileException;
}
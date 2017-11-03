package org.epcc.ps.client.shell.service;

import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;

/**
 * <p>Convert service interface.</p>
 * <p>
 *     This interface provides methods to convert data formats for application IO.
 *     A default implementation of the {@link DefaultConvertService} class is available
 *     via {@code ConvertService.DEFAULT} for convenience.
 * </p>
 *
 * @author jiahao.cao
 * @since 0.0.1
 * Created on 20/10/2017
 */
public interface ConvertService {
    ConvertService DEFAULT = new DefaultConvertService();

    /**
     * Convert landscape from given file source.
     *
     * @param fileSource file source URL
     * @return landscape converted from file source
     * @throws ConvertException if convert failed for some reason.
     */
    Landscape convertLandscapeFromFile(String fileSource) throws ConvertException;

    /**
     * Convert from landscape to PPM file.
     *
     * @param fileName file target
     * @param landscape landscape for output
     * @param species species for output
     */
    void convertLandscapeWithSpeciesToPPM(String fileName, Landscape landscape, Species species);

    /**
     * Try to terminate all the works on this object.
     */
    void shutdown();
}

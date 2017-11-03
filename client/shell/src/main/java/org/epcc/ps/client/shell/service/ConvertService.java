/*
 * Copyright (C) 2017 the predator-prey-model authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.epcc.ps.client.shell.service;

import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;

import java.util.concurrent.TimeUnit;

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
     * Await termination
     *
     * @param timeout timeout for await
     * @param timeUnit time unit
     */
    void awaitTermination(long timeout, TimeUnit timeUnit);
}

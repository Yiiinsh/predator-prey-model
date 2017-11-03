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

package org.pecc.ps.core.entity.creature;

import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.entity.creature.Species;
import org.junit.Assert;
import org.junit.Test;
import org.pecc.ps.core.AbstractTest;

/**
 * @author shaohan.yin
 * Created on 11/10/2017
 */
public class SpeciesTest extends AbstractTest {
    private CoreConfig config = CoreConfig.DEFAULT;

    @Test
    public void testPuma() {
        Assert.assertEquals("puma", Species.PUMA.getSpeciesName());
        Assert.assertEquals(config.getPumaBirthRate(), Species.PUMA.getBirthRate(), 0);
        Assert.assertEquals(config.getPumaDiffusionRate(), Species.PUMA.getDiffusionRate(), 0);
        Assert.assertEquals(config.getPumaMortalityRate(), Species.PUMA.getMortalityRate(), 0);
        Assert.assertEquals(config.getPumaPredationRate(), Species.PUMA.getPredationRate(), 0);
        Assert.assertEquals("Species[" + Species.PUMA.getSpeciesName() + "]", Species.PUMA.toString());
    }

    @Test
    public void testHare() {
        Assert.assertEquals("hare", Species.HARE.getSpeciesName());
        Assert.assertEquals(config.getHareBirthRate(), Species.HARE.getBirthRate(), 0);
        Assert.assertEquals(config.getHareDiffusionRate(), Species.HARE.getDiffusionRate(), 0);
        Assert.assertEquals(Species.NON_EXIST_DATA, Species.HARE.getPredationRate(), 0);
        Assert.assertEquals(Species.NON_EXIST_DATA, Species.HARE.getMortalityRate(), 0);
        Assert.assertEquals("Species[" + Species.HARE.getSpeciesName() + "]", Species.HARE.toString());
    }
}

package org.epcc.ps.client.shell.command;

import org.epcc.ps.client.shell.AbstractTest;
import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.client.shell.exception.SimulationSourceNotFoundException;
import org.epcc.ps.client.shell.service.ConvertService;
import org.epcc.ps.core.entity.creature.Creature;
import org.epcc.ps.core.entity.creature.CreatureFactory;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
public class TestSimulationCommand extends AbstractTest {
    private static final String FILE_FLAG = "-f";
    private static final String NON_EXISTS = "non-exists";

    private ConvertService convertService;
    private SimulationCommand simulationCommand;

    @Before
    public void setUp() throws ConvertException {
        convertService = Mockito.mock(ConvertService.class);
        simulationCommand = new SimulationCommand(convertService);


        Map<Species, Creature> creatures = new HashMap<>();
        creatures.put(Species.HARE, CreatureFactory.create(Species.HARE));
        creatures.put(Species.PUMA, CreatureFactory.create(Species.PUMA));
        Landscape landscape = new Landscape(1, 1,
                new Grid[][]{{GridFactory.create(Terrain.LAND, creatures)}});
        when(convertService.convertLandscapeFromFile(anyString())).thenReturn(landscape);
    }

    @Test
    public void testSimulationCommand() throws Exception {
        simulationCommand.simulate(new String[]{FILE_FLAG, NON_EXISTS});

        verify(convertService, times(1)).convertLandscapeFromFile(anyString());

        verify(convertService, times(26)).convertLandscapeWithSpeciesToPPM(any(), any(), any());
    }

    @Test
    public void testSimulationCommandWithWrongFlag() {
        try {
            simulationCommand.simulate(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof SimulationSourceNotFoundException);
            logger.error("Expected error:",e);
        }
    }
}

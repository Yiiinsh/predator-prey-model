package org.epcc.ps.client.shell.command;

import org.epcc.ps.client.shell.AbstractTest;
import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.client.shell.service.ConvertService;
import org.epcc.ps.core.entity.environment.Grid;
import org.epcc.ps.core.entity.environment.GridFactory;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.entity.environment.Terrain;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
public class SimulationCommandTest extends AbstractTest {
    private static final String FILE_FLAG = "-f";
    private static final String NON_EXISTS = "non-exists";

    private ConvertService convertService;
    private SimulationCommand simulationCommand;

    @Before
    public void setUp() throws ConvertException {
        convertService = Mockito.mock(ConvertService.class);
        simulationCommand = new DefaultSimulationCommand(convertService);

        Landscape landscape = new Landscape(1, 1,
                new Grid[][]{{GridFactory.create(Terrain.LAND)}});
        when(convertService.convertLandscapeFromFile(anyString())).thenReturn(landscape);
    }

    @Test
    public void testSimulationCommand() throws Exception {
        simulationCommand.simulate(new String[]{FILE_FLAG, NON_EXISTS});

        verify(convertService, times(1)).convertLandscapeFromFile(anyString());

        verify(convertService, times(24)).convertLandscapeWithSpeciesToPPM(any(), any(), any());
    }

    @Test
    public void testSimulationCommandWithWrongFlag() {
        simulationCommand.simulate(null);
    }
}

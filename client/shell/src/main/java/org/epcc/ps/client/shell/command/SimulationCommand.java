package org.epcc.ps.client.shell.command;

import org.epcc.ps.client.shell.exception.ConvertException;
import org.epcc.ps.client.shell.exception.PPMFileException;
import org.epcc.ps.client.shell.service.ConvertService;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.evolution.LandscapeEvolutionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author shaohan.yin
 * Created on 24/10/2017
 */
@ShellComponent
public class SimulationCommand extends AbstractCommand {
    @Autowired
    private ConvertService convertService;

    @ShellMethod("Evolution simulation for predator-prey model")
    public void simulate(@ShellOption({"-f", "--file"}) String fileSource,
                         @ShellOption(value = {"-i", "--interval"}, defaultValue = "50") int outputInterval)
            throws ConvertException, PPMFileException {

        Landscape landscape = convertService.convertLandscapeFromFile(fileSource);
        LandscapeEvolutionManager landscapeEvolutionManager = LandscapeEvolutionManager.create(landscape);
        landscapeEvolutionManager.evolution();

        for(int idx = 0; idx <= landscapeEvolutionManager.getSnapshots().size(); idx += outputInterval) {
            convertService.convertLandscapeWithSpeciesToPPM(
                    String.format("Iter%d-%s.ppm", idx, Species.PUMA.getSpeciesName()),
                    landscapeEvolutionManager.getSnapshots().get(idx),
                    Species.PUMA);
            convertService.convertLandscapeWithSpeciesToPPM(
                    String.format("Iter%d-%s.ppm", idx, Species.HARE.getSpeciesName()),
                    landscapeEvolutionManager.getSnapshots().get(idx),
                    Species.HARE);
        }
    }
}

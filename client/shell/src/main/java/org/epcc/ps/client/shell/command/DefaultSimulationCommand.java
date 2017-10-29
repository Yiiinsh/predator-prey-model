package org.epcc.ps.client.shell.command;

import org.apache.commons.cli.*;
import org.epcc.ps.client.shell.exception.PPMFileException;
import org.epcc.ps.client.shell.exception.SimulationSourceNotFoundException;
import org.epcc.ps.client.shell.service.ConvertService;
import org.epcc.ps.client.shell.service.DefaultConvertService;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.evolution.LandscapeEvolutionManager;

/**
 * @author shaohan.yin
 * Created on 24/10/2017
 */
public class DefaultSimulationCommand extends AbstractCommand implements SimulationCommand {
    private static final String COMMAND_NAME = "simulate";
    private static final String SIMULATION_SOURCE_FLAG = "f";
    private static final String SIMULATION_SOURCE_FLAG_LONG = "file";
    private static final String SIMULATION_OUTPUT_INTERVAL_FLAG = "i";
    private static final String SIMULATION_OUTPUT_INTERVAL_FLAG_LONG = "interval";

    private ConvertService convertService = new DefaultConvertService();
    private CommandLineParser parser;
    private Options options;
    HelpFormatter formatter;

    public DefaultSimulationCommand() {
        super();

        parser = new DefaultParser();
        options = new Options();
        formatter = new HelpFormatter();

        options.addOption(SIMULATION_SOURCE_FLAG, SIMULATION_SOURCE_FLAG_LONG,
                true, "Specified landscape generation file source.");
        options.addOption(SIMULATION_OUTPUT_INTERVAL_FLAG, SIMULATION_OUTPUT_INTERVAL_FLAG_LONG,
                true, "(Optional) PPM output interval.");
    }

    protected DefaultSimulationCommand(ConvertService convertService) {
        this();
        this.convertService = convertService;
    }

    @Override
    public void simulate(String[] args) throws Exception {
        try {
            CommandLine commandLine = parser.parse(options, args);
            check(commandLine);

            String fileSource = commandLine.getOptionValue(SIMULATION_SOURCE_FLAG);
            int interval = Integer.parseInt(
                    commandLine.getOptionValue(SIMULATION_OUTPUT_INTERVAL_FLAG, "100"));

            Landscape landscape = convertService.convertLandscapeFromFile(fileSource);
            LandscapeEvolutionManager landscapeEvolutionManager = LandscapeEvolutionManager.create(landscape);
            landscapeEvolutionManager.evolution();

            for(int idx = 0; idx <= landscapeEvolutionManager.getSnapshots().size(); idx += interval){
                outputSnapshotsAsPPM(idx, landscapeEvolutionManager.getSnapshots().get(idx));

                calculateAverageDensity(idx, landscapeEvolutionManager.getSnapshots().get(idx));
            }


        } catch (Exception e) {
            logger.error("Simulation failed.", e.getMessage());
            formatter.printHelp(COMMAND_NAME, options);
            throw e;
        }
    }

    private void check(CommandLine commandLine) throws SimulationSourceNotFoundException {
        if(!commandLine.hasOption(SIMULATION_SOURCE_FLAG)) {
            throw new SimulationSourceNotFoundException("Simulation source file must be specified with -f flag.");
        }
    }

    private void outputSnapshotsAsPPM(int idx, Landscape landscape) throws PPMFileException {
        convertService.convertLandscapeWithSpeciesToPPM(
                String.format("%d-hare.ppm", idx),
                landscape,
                Species.HARE
        );
        convertService.convertLandscapeWithSpeciesToPPM(
                String.format("%d-puma.ppm", idx),
                landscape,
                Species.PUMA
        );
    }

    private void calculateAverageDensity(int idx, Landscape landscape) {
        double hare = 0, puma = 0;
        for(int xIdx = 0; xIdx != landscape.getLength(); ++xIdx) {
            for(int yIdx = 0; yIdx != landscape.getWidth(); ++yIdx) {
                hare += landscape.getGrids()[xIdx][yIdx].getDensity(Species.HARE);
                puma += landscape.getGrids()[xIdx][yIdx].getDensity(Species.PUMA);
            }
        }
        int landscapeSize = landscape.getLength() * landscape.getWidth();
        logger.info(String.format("Average Density %d: PUMA %.3f HARE %.3f", idx,
                puma / landscapeSize, hare / landscapeSize));
    }

}

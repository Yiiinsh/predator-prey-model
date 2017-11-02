package org.epcc.ps.client.shell.command;

import org.apache.commons.cli.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.epcc.ps.client.shell.exception.SimulationSourceNotFoundException;
import org.epcc.ps.client.shell.service.ConvertService;
import org.epcc.ps.core.config.CoreConfig;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.evolution.LandscapeEvolutionManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

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
    private static final String SIMULATION_REPORT_FLAG = "r";
    private static final String SIMULATION_REPORT_FLAG_LONG = "report";
    private static final String VELOCITY_TEMPLATE_FILE = "report-template.vm";

    private ConvertService convertService = ConvertService.DEFAULT;
    private CoreConfig config = CoreConfig.DEFAULT;

    private CommandLineParser parser;
    private Options options;
    private HelpFormatter formatter;

    public DefaultSimulationCommand() {
        super();

        parser = new DefaultParser();
        options = new Options();
        formatter = new HelpFormatter();

        options.addOption(SIMULATION_SOURCE_FLAG, SIMULATION_SOURCE_FLAG_LONG,
                true, "Specified landscape generation file source.");
        options.addOption(SIMULATION_OUTPUT_INTERVAL_FLAG, SIMULATION_OUTPUT_INTERVAL_FLAG_LONG,
                true, "(Optional) PPM output interval.Default value is 100.");
        options.addOption(SIMULATION_REPORT_FLAG, SIMULATION_REPORT_FLAG_LONG,
                false, "(Optional) Generate report.");
    }

    protected DefaultSimulationCommand(ConvertService convertService) {
        this();
        this.convertService = convertService;
    }

    @Override
    public void simulate(String[] args) {
        try {
            CommandLine commandLine = parser.parse(options, args);
            check(commandLine);

            String fileSource = commandLine.getOptionValue(SIMULATION_SOURCE_FLAG);
            int interval = Integer.parseInt(
                    commandLine.getOptionValue(SIMULATION_OUTPUT_INTERVAL_FLAG, "100"));

            Landscape landscape = convertService.convertLandscapeFromFile(fileSource);
            LandscapeEvolutionManager landscapeEvolutionManager = LandscapeEvolutionManager.create(landscape);

            double start = config.getLandscapeEvolutionTimeStart();
            double end = config.getLandscapeEvolutionTimeEnd();
            double timeStep = config.getLandscapeEvolutionTimeStep();
            for (int idx = 1; start <= end; ++idx, start += timeStep) {
                landscapeEvolutionManager.evolution(timeStep);

                if (0 == idx % interval) {
                    convertService.convertLandscapeWithSpeciesToPPM(String.format("%d-hare.ppm", idx),
                            landscape, Species.HARE);
                    convertService.convertLandscapeWithSpeciesToPPM(String.format("%d-puma.ppm", idx),
                            landscape, Species.PUMA);

                    logger.info(String.format("Average Density On Evo-%d: PUMA %.3f HARE %.3f", idx,
                            ((LinkedList)landscapeEvolutionManager.getAverages(Species.PUMA)).getLast(),
                            ((LinkedList)landscapeEvolutionManager.getAverages(Species.HARE)).getLast()));
                }
            }

            if (commandLine.hasOption(SIMULATION_REPORT_FLAG)) {
                generateReport(landscapeEvolutionManager);
            }

        } catch (Exception e) {
            logger.error("Simulation failed.", e);
            formatter.printHelp(COMMAND_NAME, options);
        }
    }

    private void check(CommandLine commandLine) throws SimulationSourceNotFoundException {
        if (!commandLine.hasOption(SIMULATION_SOURCE_FLAG)) {
            throw new SimulationSourceNotFoundException("Simulation source file must be specified with -f flag.");
        }
    }

    private void generateReport(LandscapeEvolutionManager landscapeEvolutionManager) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(VELOCITY_TEMPLATE_FILE, "UTF-8");

        VelocityContext context = new VelocityContext();
        context.put("hareAverageDensities", landscapeEvolutionManager.getAverages(Species.HARE));
        context.put("pumaAverageDensities", landscapeEvolutionManager.getAverages(Species.PUMA));

        try (FileWriter fileWriter = new FileWriter("report.html")) {
            StringWriter stringWriter = new StringWriter();
            template.merge(context, stringWriter);
            fileWriter.write(stringWriter.toString());
        } catch (IOException e) {
            logger.error("Cannot write report to file.", e);
        }
    }

    private List<Double>[][] generateSpeciesDataForReport(List<Landscape> landscapes, Species species) {
        int length = landscapes.get(0).getLength();
        int width = landscapes.get(0).getWidth();
        List<Double>[][] data = new List[length][width];

        for (Landscape landscape : landscapes) {
            for (int xIdx = 0; xIdx != landscape.getLength(); ++xIdx) {
                for (int yIdx = 0; yIdx != landscape.getWidth(); ++yIdx) {
                    if (null == data[xIdx][yIdx]) {
                        data[xIdx][yIdx] = new LinkedList<>();
                    }
                    data[xIdx][yIdx].add(landscape.getGrids()[xIdx][yIdx].getDensity(species));
                }
            }
        }

        return data;
    }
}

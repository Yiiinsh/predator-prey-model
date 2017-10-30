package org.epcc.ps.client.shell.command;

import org.apache.commons.cli.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.epcc.ps.client.shell.exception.PPMFileException;
import org.epcc.ps.client.shell.exception.SimulationSourceNotFoundException;
import org.epcc.ps.client.shell.service.ConvertService;
import org.epcc.ps.client.shell.service.DefaultConvertService;
import org.epcc.ps.core.entity.creature.Species;
import org.epcc.ps.core.entity.environment.Landscape;
import org.epcc.ps.core.evolution.LandscapeEvolutionManager;

import java.io.StringWriter;
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
            landscapeEvolutionManager.evolution();

            generateResult(landscapeEvolutionManager.getSnapshots(), interval);

            if(commandLine.hasOption(SIMULATION_REPORT_FLAG)) {
                generateReport(landscapeEvolutionManager.getSnapshots());
            }

        } catch (Exception e) {
            logger.error("Simulation failed.", e);
            formatter.printHelp(COMMAND_NAME, options);
        }
    }

    private void check(CommandLine commandLine) throws SimulationSourceNotFoundException {
        if(!commandLine.hasOption(SIMULATION_SOURCE_FLAG)) {
            throw new SimulationSourceNotFoundException("Simulation source file must be specified with -f flag.");
        }
    }

    private void generateResult(List<Landscape> landscapes, int interval) throws PPMFileException {
        for(int idx = 0; idx <= landscapes.size(); idx += interval) {
            outputSnapshotsAsPPM(String.format("%d-hare.ppm", idx), landscapes.get(idx), Species.HARE);
            outputSnapshotsAsPPM(String.format("%d-puma.ppm", idx), landscapes.get(idx), Species.PUMA);

            logger.info(String.format("Average Density %d: PUMA %.3f HARE %.3f", idx,
                    calculateAverageDensity(landscapes.get(idx), Species.PUMA),
                    calculateAverageDensity(landscapes.get(idx), Species.HARE)));
        }
    }

    private void outputSnapshotsAsPPM(String fileName, Landscape landscape, Species species) throws PPMFileException {
        convertService.convertLandscapeWithSpeciesToPPM(
                fileName,
                landscape,
                species
        );
    }

    private double calculateAverageDensity(Landscape landscape, Species species) {
        double result = 0;
        for(int xIdx = 0; xIdx != landscape.getLength(); ++xIdx) {
            for(int yIdx = 0; yIdx != landscape.getWidth(); ++yIdx) {
                result += landscape.getGrids()[xIdx][yIdx].getDensity(species);
            }
        }
        return result / (landscape.getLength() * landscape.getWidth());
    }

    private void generateReport(List<Landscape> landscapes) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(VELOCITY_TEMPLATE_FILE);

        VelocityContext context = new VelocityContext();
        context.put("landscapes", landscapes);

        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        System.out.println(stringWriter.toString());
    }

}

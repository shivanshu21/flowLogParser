package com.illumio;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;

public class flowLogParser {
    public static void main(String[] args) {
        Options options = new Options();
        Option logfile = new Option("L", "logfile", true, "path to the log file");
        logfile.setRequired(true);
        options.addOption(logfile);
        Option tagfile = new Option("T", "tagfile", true, "path to the tag file");
        tagfile.setRequired(true);
        options.addOption(tagfile);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            flowLogHelper helper = new flowLogHelper(
                cmd.getOptionValue("logfile"),
                cmd.getOptionValue("tagfile")
            );
            helper.start();
        } catch (Exception e) {
            System.out.println("ERROR: Cannot parse command line input: " + e.getMessage());
            System.exit(1);
        }
    }
}


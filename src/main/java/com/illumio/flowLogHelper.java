package com.illumio;

import com.illumio.providers.protocolProvider;
import com.illumio.types.destination;
import com.illumio.types.protocol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class flowLogHelper {
    Map<String, Integer> tagCounts = new HashMap<>();
    Map<destination, Integer> destinationCount = new HashMap<>();
    Map<destination, List<String>> destinationTags = new HashMap<>();
    protocolProvider protoProvider;
    String logFilePath;
    String tagFilePath;

    flowLogHelper(String logFilePath, String tagFilePath) {
        protoProvider = new protocolProvider();
        this.logFilePath = logFilePath;
        this.tagFilePath = tagFilePath;
    }

    public void start() throws IOException {
        buildDestinationTags();
        readLogsAndCount();
        // write output
    }

    public void stop() {

    }

    private void buildDestinationTags() throws IOException {
        // Attempt to read the CSV file with tagging information and build a map
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(this.tagFilePath))) {
            // Read and ignore the header
            line = br.readLine();

            // now continue with reading csv
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(
                    "[INFO] Reading DST port: " +
                        values[0] +
                        ", Protocol: "
                        + values[1] +
                        ", Tag: " +
                        values[2]
                );
                protocol x = protoProvider.getProtocolByName(values[1]);
                destination d = new destination(x, Integer.parseInt(values[0]));
                if (!destinationTags.containsKey(d)) {
                    destinationTags.put(d, new ArrayList<>());
                }
                destinationTags.get(d).add(values[2]);
            }
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("[ERROR] Cannot read CSV tag file: " + e.getMessage());
            throw e;
        }
    }

    private void readLogsAndCount() {
        final int VERSION_POS = 0;
        final int DST_PORT_POS = 6;
        final int PROTOCOL_POS = 7;
        final int VERSION_2_LENGTH = 14;

        try (BufferedReader br = new BufferedReader(new FileReader(this.logFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                List<String> lineTokens = new ArrayList<>();
                while (tokenizer.hasMoreTokens()) {
                    lineTokens.add(tokenizer.nextToken());
                }
                // Number of tokens precheck
                if (lineTokens.size() != VERSION_2_LENGTH) {
                    System.out.println(
                        "[WARN] Failed parsing line due to unmatched token size: " +
                        lineTokens.size() + " input: " +
                        line
                    );
                    // Process other lines
                    continue;
                }
                // version must be version 2 only
                // todo: Add a supported versions list with only 2 as member for now
                if (Integer.parseInt(lineTokens.get(VERSION_POS)) != 2) {
                    System.out.println(
                        "[WARN] Parsed line is not from log version 2: " +
                        line
                    );
                    // Process other lines
                    continue;
                }

                boolean skipLine = false;
                destination lineDST = null;
                try {
                    int linePort = Integer.parseInt(lineTokens.get(DST_PORT_POS));
                    int lineProto = Integer.parseInt(lineTokens.get(PROTOCOL_POS));
                    protocol x = protoProvider.getProtocolByNumber(lineProto);
                    lineDST = new destination(x, linePort);
                } catch (IllegalArgumentException e) {
                    skipLine = true;
                }
                if (skipLine) {
                    continue;
                }
                // bump up tag count
                if (lineDST != null && destinationTags.containsKey(lineDST)) {
                    for (String tag : destinationTags.get(lineDST)) {
                        if (!tagCounts.containsKey(tag)) {
                            tagCounts.put(tag, 0);
                        }
                        tagCounts.put(tag, tagCounts.get(tag) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Cannot read log file: " + e.getMessage());
        }
    }
}

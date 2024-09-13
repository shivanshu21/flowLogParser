# FlowLogParser

FlowLogParser is a Java-based application designed to parse network flow logs.
As requested, it parses log lines for destination port and protocol values and matches them to predefined tags using a lookup table.

## Design choices and assumptions
- Why did you not choose python?
  Java garbage collection and memory management are more mature than Python. For larger files, maybe 10 GB, this will be a better choice.
  Java has also been my primary language for past few years.
- Why declare so many types? For extensibility and modularity. It also provides automatic type safety and opportunity to check our inputs.
- For "Port/Protocol Combination Counts" I have assumed the meaning to be destination port / protocol combination counts
- It is assumed that each log line will have same number of tokens and positions of values will not change. If this changes, the line is skipped.
- Added a precheck to only parse lines with version 2

## Requirements
- Java 11 or above
- Maven (use this guide: https://www.digitalocean.com/community/tutorials/install-maven-mac-os)

## How do I run this?
1. Clone the repository:
   git clone https://github.com/shivanshu21/flowLogParser.git
2. Edit "runner.sh" to provide your input files
3. Run "runner.sh"
4. Expect output as "output.txt" in your current working directory


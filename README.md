# Assignment for PSS

This project is a middleware Java console application which transforms an order XML file containing products for various 
suppliers into multiple XML files containing the products for each supplier.

The application uses the FileWatcher API to listen to changes in the input_orders directory.
It uses JAXB to serialize and deserialize the XML files.
It also uses Lombok to generate constructors, getters and setters for better readability.

The transformation from the input order XML to each supplier file is implemented using a Map. The key is the supplier name
and the value is an ordered TreeSet containing the supplier's products.
The ordering of the products is done using the DescendingOutputProductComparator class.

## Prerequisites

* Maven 3+
* JDK 11

## Usage

1. Run 'mvn package' in the project root directory.
2. Run 'cd target'.
3. Run 'java -jar assignment_pss-1.0-SNAPSHOT-jar-with-dependencies.jar'
4. Copy and paste the orders XML file (for example the one in src/main/java/resources) in the newly created input_orders directory.
5. The output files will appear in the output_orders directory.

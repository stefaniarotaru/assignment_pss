package org.stefania.assignment.supplier.service;

import org.stefania.assignment.common.service.SerializationService;
import org.stefania.assignment.supplier.domain.SupplierOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class SupplierService {

    private static final String OUTPUT_DIR = "output_orders/";

    private final SerializationService serializationService = new SerializationService();

    public void writeSupplierFiles(Map<String, SupplierOutput> productsBySuppliers, String fileNumber) {
        try {
            if (!Files.exists(Path.of(OUTPUT_DIR))) {
                Files.createDirectory(Path.of(OUTPUT_DIR));
            }
        } catch (IOException e) {
            System.err.println("Could not create output directory. " + e);
            return;
        }
        productsBySuppliers.forEach((supplier, products) -> {
                    String outputFileName = OUTPUT_DIR + supplier + fileNumber + ".xml";
                    System.out.println("Writing supplier file: " + outputFileName);
                    serializationService.writeSupplierOutputToFile(products, outputFileName);
                }
        );
    }
}

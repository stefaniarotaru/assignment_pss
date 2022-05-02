package org.stefania.assignment.supplier.service;

import org.stefania.assignment.common.service.SerializationService;
import org.stefania.assignment.supplier.domain.SupplierOutput;

import java.util.Map;

public class SupplierService {

    private static final String OUTPUT_DIR = "output_orders/";

    private final SerializationService serializationService = new SerializationService();

    public void writeSupplierFiles(Map<String, SupplierOutput> productsBySuppliers, String fileNumber) {
        productsBySuppliers.forEach((supplier, products) -> {
                    String outputFileName = OUTPUT_DIR + supplier + fileNumber + ".xml";
                    System.out.println("Writing supplier file: " + outputFileName);
                    serializationService.writeSupplierOutputToFile(products, outputFileName);
                }
        );
    }
}

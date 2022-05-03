package org.stefania.assignment.supplier.service;

import lombok.extern.slf4j.Slf4j;
import org.stefania.assignment.common.service.SerializationService;
import org.stefania.assignment.supplier.domain.SupplierOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Slf4j
public class SupplierService {

    private static final String OUTPUT_DIR = "output_orders/";

    private final SerializationService serializationService = new SerializationService();

    /**
     * Writes a file for each supplier, containing its products.
     * Creates the output directory if it doesn't exist.
     *
     * @param productsBySuppliers - Map with key being the supplier's name and value representing the supplier's list of products
     * @param fileNumber - the number generated from the orders file name, will be used in the supplier file name
     */
    public void writeSupplierFiles(Map<String, SupplierOutput> productsBySuppliers, String fileNumber) {
        try {
            if (!Files.exists(Path.of(OUTPUT_DIR))) {
                Files.createDirectory(Path.of(OUTPUT_DIR));
            }
        } catch (IOException e) {
            log.error("Could not create output directory. ", e);
            return;
        }
        productsBySuppliers.forEach((supplier, products) -> {
                    String outputFileName = OUTPUT_DIR + supplier + fileNumber + ".xml";
                    log.info("Writing supplier file: {}", outputFileName);
                    serializationService.writeSupplierOutputToFile(products, outputFileName);
                }
        );
    }
}

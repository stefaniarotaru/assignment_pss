package org.stefania.assignment;

import lombok.extern.log4j.Log4j2;
import org.stefania.assignment.common.service.SerializationService;
import org.stefania.assignment.order.domain.Orders;
import org.stefania.assignment.order.service.OrdersService;
import org.stefania.assignment.supplier.domain.SupplierOutput;
import org.stefania.assignment.supplier.service.SupplierService;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Log4j2
public class Main {

    public static final String INPUT_ORDERS_DIR = "input_orders/";

    public static void main(String[] args) throws InterruptedException {
        log.info("Starting application");
        WatchService watchService;
        try {
            watchService = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            log.error("Could not get WatchService.");
            return;
        }
        Path inputPath = Paths.get(INPUT_ORDERS_DIR);
        try {
            if (!Files.exists(inputPath)) {
                Files.createDirectory(inputPath);
            }
        } catch (IOException e) {
            log.error("Could not create input directory. ", e);
            return;
        }

        try {
            inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            log.error("Could not register file watcher.");
            return;
        }
        log.info("Listening to changes on {}", inputPath);

        SerializationService serializationService = new SerializationService();
        OrdersService ordersService = new OrdersService();
        SupplierService supplierService = new SupplierService();
        Pattern notDigitsRegex = Pattern.compile("[^0-9]");

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                String fileName = event.context().toString();
                String ordersPath = INPUT_ORDERS_DIR + fileName;
                Optional<Orders> ordersOpt = serializationService.getOrdersFromPath(ordersPath);
                ordersOpt.ifPresent(orders -> {
                    Map<String, SupplierOutput> productsBySuppliers = ordersService.getProductsBySuppliers(orders);
                    String fileNumber = notDigitsRegex.matcher(fileName).replaceAll("");
                    supplierService.writeSupplierFiles(productsBySuppliers, fileNumber);
                    log.info("Processing done.");
                });
            }
            key.reset();
        }
    }
}

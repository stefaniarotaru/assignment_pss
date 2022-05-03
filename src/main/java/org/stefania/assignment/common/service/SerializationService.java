package org.stefania.assignment.common.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import org.stefania.assignment.order.domain.Orders;
import org.stefania.assignment.supplier.domain.SupplierOutput;

import java.io.File;
import java.util.Optional;

@Slf4j
public class SerializationService {

    /**
     * Deserialize an Orders object from a file path
     *
     * @param path - the path of the orders input file
     * @return Optional containing the orders or Optional.empty() if deserialization failed
     */
    public Optional<Orders> getOrdersFromPath(String path) {
        log.info("Processing {}", path);
        try {
            JAXBContext context = JAXBContext.newInstance(Orders.class);
            Orders orders = (Orders) context.createUnmarshaller().unmarshal(new File(path));
            return Optional.of(orders);
        } catch (JAXBException e) {
            log.error("Exception while deserializing orders: ", e);
            return Optional.empty();
        }
    }

    /**
     * Serialize SupplierOutput to given filePath
     *
     * @param supplierOutput - the list of products belonging to one supplier
     * @param filePath - the path of the output supplier file
     */
    public void writeSupplierOutputToFile(SupplierOutput supplierOutput, String filePath) {
        File outputFile = new File(filePath);
        try {
            JAXBContext context = JAXBContext.newInstance(SupplierOutput.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(supplierOutput, outputFile);
        } catch (JAXBException e) {
            log.error("Could not Serialize Supplier Output: ", e);
        }
    }
}

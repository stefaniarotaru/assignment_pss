package org.stefania.assignment.common.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.stefania.assignment.order.domain.Orders;
import org.stefania.assignment.supplier.domain.SupplierOutput;

import java.io.File;
import java.util.Optional;


public class SerializationService {

    public Optional<Orders> getOrdersFromPath(String path) {
        System.out.println("Processing " + path);
        try {
            JAXBContext context = JAXBContext.newInstance(Orders.class);
            Orders orders = (Orders) context.createUnmarshaller().unmarshal(new File(path));
            return Optional.of(orders);
        } catch (JAXBException e) {
            System.err.println("Exception while deserializing orders: " + e);
            return Optional.empty();
        }
    }

    public void writeSupplierOutputToFile(SupplierOutput supplierOutput, String filePath) {
        File outputFile = new File(filePath);
        try {
            JAXBContext context = JAXBContext.newInstance(SupplierOutput.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(supplierOutput, outputFile);
        } catch (JAXBException e) {
            System.err.println("Could not Serialize Supplier Output: " + e);
        }
    }
}

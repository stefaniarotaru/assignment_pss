package org.stefania.assignment.order.service;

import org.stefania.assignment.order.DescendingOutputProductComparator;
import org.stefania.assignment.order.domain.InputProduct;
import org.stefania.assignment.order.domain.Order;
import org.stefania.assignment.order.domain.Orders;
import org.stefania.assignment.supplier.domain.OutputProduct;
import org.stefania.assignment.supplier.domain.SupplierOutput;

import java.util.*;

public class OrdersService {
    /**
     * Group products from Orders object by supplier name
     *
     * @param orders - object containing orders
     * @return grouped products
     */
    public Map<String, SupplierOutput> getProductsBySuppliers(Orders orders) {
        Map<String, SupplierOutput> productsBySuppliers = new HashMap<>();

        for (Order order : orders.getOrders()) {
            for (InputProduct inputProduct : order.getProducts()) {
                OutputProduct outputProduct = new OutputProduct();

                outputProduct.setDescription(inputProduct.getDescription());
                outputProduct.setGtin(inputProduct.getGtin());
                outputProduct.setPrice(inputProduct.getPrice());
                outputProduct.setOrderId(order.getId());
                outputProduct.setOrderCreatedAt(order.getCreated());

                String supplier = inputProduct.getSupplier();
                if (productsBySuppliers.containsKey(supplier)) {
                    productsBySuppliers.get(supplier).getProducts().add(outputProduct);
                } else {
                    Set<OutputProduct> outputProducts = new TreeSet<>(DescendingOutputProductComparator.getInstance());
                    outputProducts.add(outputProduct);
                    SupplierOutput supplierOutput = new SupplierOutput(outputProducts);
                    productsBySuppliers.put(supplier, supplierOutput);
                }
            }
        }

        return productsBySuppliers;
    }
}

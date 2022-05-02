package org.stefania.assignment.order;

import org.stefania.assignment.supplier.domain.OutputProduct;

import java.util.Comparator;

public class DescendingOutputProductComparator implements Comparator<OutputProduct> {
    private static DescendingOutputProductComparator instance;
    private DescendingOutputProductComparator() {}

    @Override
    public int compare(OutputProduct o1, OutputProduct o2) {
        int timestampCompareResult = o1.getOrderCreatedAt().compareTo(o2.getOrderCreatedAt());
        if (timestampCompareResult != 0) {
            return -timestampCompareResult;
        }

        return o1.getPrice().compareTo(o2.getPrice());
    }

    public static DescendingOutputProductComparator getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new DescendingOutputProductComparator();
        return instance;
    }
}

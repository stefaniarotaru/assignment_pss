package org.stefania.assignment.order.domain;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.stefania.assignment.common.LocalDateTimeAdapter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement(name = "product", type = InputProduct.class)
    private List<InputProduct> products;

    @XmlAttribute
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime created;

    @XmlAttribute(name = "ID")
    private String id;

}

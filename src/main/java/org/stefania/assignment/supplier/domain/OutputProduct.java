package org.stefania.assignment.supplier.domain;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import org.stefania.assignment.common.domain.Price;

import java.time.LocalDateTime;

@Data
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class OutputProduct {

    @XmlElement
    private String description;
    @XmlElement
    private String gtin;
    @XmlElement
    private Price price;
    @XmlElement
    private String orderId;
    @XmlTransient
    private LocalDateTime orderCreatedAt;

}

package org.stefania.assignment.order.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.stefania.assignment.common.domain.Price;


@Data
@NoArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class InputProduct {

    @XmlElement
    private String description;

    @XmlElement
    private String gtin;

    @XmlElement
    private Price price;

    @XmlElement
    private String supplier;

}

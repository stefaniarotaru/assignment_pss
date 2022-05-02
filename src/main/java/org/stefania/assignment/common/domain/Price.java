package org.stefania.assignment.common.domain;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@XmlRootElement(name = "price")
@XmlAccessorType(XmlAccessType.FIELD)
public class Price implements Comparable<Price> {

    @XmlAttribute
    private String currency;

    @XmlValue
    private Float value;

    @Override
    public int compareTo(Price o) {
        return Float.compare(this.value, o.getValue());
    }
}

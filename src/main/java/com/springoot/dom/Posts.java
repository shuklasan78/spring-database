package com.springoot.dom;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Data
@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Posts implements Serializable {
    private List<RowId> row;
}
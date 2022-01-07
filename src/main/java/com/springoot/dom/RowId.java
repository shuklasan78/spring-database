package com.springoot.dom;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@Data
public class RowId implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlAttribute(name = "Id")
    String Id;
    @XmlAttribute(name = "PostTypeId")
    String PostTypeId;
    @XmlAttribute(name = "CreationDate")
    String CreationDate;
    @XmlAttribute(name = "Score")
    String Score;
    @XmlAttribute(name = "ViewCount")
    String ViewCount;
    @XmlAttribute(name = "Body")
    String Body;
    @XmlAttribute(name = "ParentId")
    String ParentId;
    @XmlAttribute(name = "AcceptedAnswerId")
    String AcceptedAnswerId;
    @XmlAttribute(name = "OwnerUserId")
    String OwnerUserId;
    @XmlAttribute(name = "LastEditorUserId")
    String LastEditorUserId;
    @XmlAttribute(name = "LastActivityDate")
    String LastActivityDate;
    @XmlAttribute(name = "Title")
    String Title;
    @XmlAttribute(name = "Tags")
    String Tags;
    @XmlAttribute(name = "AnswerCount")
    String AnswerCount;
    @XmlAttribute(name = "CommentCount")
    String CommentCount;


}


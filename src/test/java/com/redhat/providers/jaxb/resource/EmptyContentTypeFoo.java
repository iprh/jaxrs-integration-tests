package com.redhat.providers.jaxb.resource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmptyContentTypeFoo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

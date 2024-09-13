package com.illumio.types;

import com.sun.tools.javac.util.StringUtils;

public class protocol {
    private String name;
    private Integer number;
    public protocol (String n, Integer i) throws IllegalArgumentException {
        // IANA defines protocol numbers from 0 to 255
        // https://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml
        if (i < 0 || i > 255) {
            throw new IllegalArgumentException();
        }
        this.name = StringUtils.toLowerCase(n);
        this.number = i;
    }
    public protocol (String n) {
        this.name = StringUtils.toLowerCase(n);
        this.number = null;
    }

    public String getName() {
        return this.name;
    }

    public Integer getNumber() {
        return this.number;
    }

    public boolean equals(protocol obj) {
        return this.getName().equals(obj.getName());
    }
}

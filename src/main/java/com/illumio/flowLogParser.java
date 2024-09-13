package com.illumio;

import com.illumio.types.ipv4Address;

public class flowLogParser {
    public static void main(String[] args) {
        ipv4Address x = new ipv4Address("10.1.1.10");

        System.out.println(x.getAddress());
    }
}


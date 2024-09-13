package com.illumio.providers;

import com.illumio.types.protocol;

import java.util.HashMap;
import java.util.Map;

public class protocolProvider {
    private Map<Integer, String> protocolMap = new HashMap<>();

    public protocolProvider() {
        protocolMap.put(1, "ICMP");
        protocolMap.put(6, "TCP");
        protocolMap.put(17, "UDP");
        protocolMap.put(2, "IGMP");
        protocolMap.put(43, "IPv6-Route");
        protocolMap.put(44, "IPv6-Frag");
        protocolMap.put(89, "OSPF");
        protocolMap.put(58, "ICMPv6");
        protocolMap.put(143, "Ethernet");
    }

    // The flow logs mention protocol as a number, so we need
    // something to fetch us a common name for well known protocols
    public protocol getProtocolByNumber(Integer num) throws IllegalArgumentException {
        String pname = "";
        if (!protocolMap.containsKey(num)) {
            pname = "Proto-" + String.valueOf(num);
        } else {
            pname = protocolMap.get(num);
        }
        protocol x = null;
        try {
            x = new protocol(pname, num);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Cannot create protocol with integer value: " + num);
            throw new IllegalArgumentException("Protocol does not exist");
        }
        return x;
    }

    // If you know the protocol name already, just create an object
    public protocol getProtocolByName(String name) {
        return new protocol(name);
    }
}

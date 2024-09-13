package com.illumio.types;

import com.google.common.net.InetAddresses;
import java.net.InetAddress;
import java.net.Inet6Address;

public class ipv6Address implements ipAddress {
    private InetAddress address;

    public ipv6Address(String address) {
        if (isValid(address)) {
            this.address = InetAddresses.forString(address);
        } else {
            throw new IllegalArgumentException("Input not in IPv6 format");
        }
    }

    public String getAddress() {
        return InetAddresses.toAddrString(address);
    }

    public void setAddress(String address) {
        if (isValid(address)) {
            this.address = InetAddresses.forString(address);
        } else {
            throw new IllegalArgumentException("Input not in IPv6 format");
        }
    }

    private boolean isValid(String address) {
        try {
            InetAddress addrObj = InetAddresses.forString(address);
            if (addrObj instanceof Inet6Address) {
                 return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("WARNING: Address string is not valid: " + address);
        }
        return false;
    }
}


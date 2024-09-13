package com.illumio.types;

public class destination implements Comparable<destination> {
    private protocol proto;
    private Integer port;

    public destination(protocol proto, Integer port) throws IllegalArgumentException {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Unexpected port");
        }
        this.proto = proto;
        this.port = port;
    }

    /*
    The genuine purpose of comparison here is equality.
    We define this type to be used as a Map key.
     */
    @Override
    public int compareTo(destination o) {
        if (!this.proto.equals(o.proto)) {
            return 1;
        }
        if (this.port > o.port) {
            return 1;
        } else if (this.port < o.port) {
            return -1;
        }
        return 0;
    }
}

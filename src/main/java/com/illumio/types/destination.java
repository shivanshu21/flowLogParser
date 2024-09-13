package com.illumio.types;

import java.util.Objects;

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
        if (!this.proto.getName().equals(o.proto.getName())) {
            return 1;
        }
        if (this.port > o.port) {
            return 1;
        } else if (this.port < o.port) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        destination that = (destination) o;
        return Objects.equals(proto.getName(), that.proto.getName()) && Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proto.getName(), port);
    }

    public String toString() {
        return String.valueOf(port) + "," + this.proto.getName() + ",";
    }
}

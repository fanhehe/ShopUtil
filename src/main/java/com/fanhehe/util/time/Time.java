package com.fanhehe.util.time;

import java.time.Instant;

public final class Time {

    public static int makeUnixTimestamp() {
        return (int) (Instant.now().toEpochMilli() / 1000);
    }
}

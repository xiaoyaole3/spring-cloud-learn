package com.wander;

import org.junit.Test;

import java.time.ZonedDateTime;

public class ZonedDateTimeTest {

    @Test
    public void getZonedDateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}

package com.saran.Bookstoreapi.Actuators;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    public CustomMetrics(MeterRegistry meterRegistry) {
        meterRegistry.counter("books.added.counter").increment(1);
        meterRegistry.gauge("books.available",100);
    }
}

package com.unipath.ms_unipath.rest.resources.test;

import java.time.LocalDateTime;
import java.util.List;

public record TestResponse(List<TestResource> prediction, String username, Integer age, String fechaRegistro) {
}

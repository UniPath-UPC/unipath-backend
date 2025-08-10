package com.unipath.ms_unipath.rest.resources.test;

import java.util.List;

public record TestHistorialDocente(Long id_test, List<TestResource> prediction,Long userId, String username, Integer age, String fechaRegistro, Integer favorite, String areaInteres) {
}

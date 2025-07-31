package com.unipath.ms_unipath.rest.resources.test;

import java.util.List;

public record TestHistorial(Long id_test,List<TestResource> prediction, String username, Integer age, String fechaRegistro, Integer favorite) {
}

package com.unipath.ms_unipath.rest.resources.test;

import java.time.LocalDateTime;

public record TotalTestForReports(Long studentId, String fullName, Long test_id, String area1, String area2, LocalDateTime fechaRegistro, Integer flg_favorites, String genre, Float hitRate, Long careerId, String careerName) {
}

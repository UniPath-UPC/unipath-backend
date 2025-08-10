package com.unipath.ms_unipath.domain.services;

import com.unipath.ms_unipath.rest.resources.DTOs.AnswerChasideDetailDTO;
import com.unipath.ms_unipath.rest.resources.test.*;

import java.util.List;

public interface TestService {
    TestResponse getPredict (TestRequest request, Long user_id);
    String evaluateChaside (List<AnswerChasideDetailDTO> chasideDetails);
    List<TestResource> externalApiCall (CreateTestResource resource);
    List<TestHistorial> getTestHistorial(Long user_id);
    void changeFavorite (Long test_id);
    List<TestHistorialDocente> getTestHistorialDocente(Long user_id);
    List<TotalTestForReports> getTestforReports(Long user_id);
}

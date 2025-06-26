package com.unipath.ms_unipath.domain.services;

import com.unipath.ms_unipath.rest.resources.DTOs.AnswerChasideDetailDTO;
import com.unipath.ms_unipath.rest.resources.test.CreateTestResource;
import com.unipath.ms_unipath.rest.resources.test.TestRequest;
import com.unipath.ms_unipath.rest.resources.test.TestResource;
import com.unipath.ms_unipath.rest.resources.test.TestResponse;

import java.util.List;

public interface TestService {
    TestResponse getPredict (TestRequest request, Long user_id);
    String evaluateChaside (List<AnswerChasideDetailDTO> chasideDetails);
    List<TestResource> externalApiCall (CreateTestResource resource);
}

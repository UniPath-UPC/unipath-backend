package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.services.TestService;
import com.unipath.ms_unipath.repositories.TestRepository;
import com.unipath.ms_unipath.rest.resources.test.*;
import com.unipath.ms_unipath.rest.resources.university.UniversityResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/test", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Test", description = "Test Management Endpoints")
public class TestController {

    private final TestService testService;


    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/{user_id}")
    ResponseEntity<TestResponse> predictTest(@RequestBody TestRequest testResource, @PathVariable Long user_id) {
        TestResponse testRespond = testService.getPredict(testResource,user_id);

        return new ResponseEntity<>(testRespond, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    ResponseEntity<List<TestHistorial>> getHistorialTest(@PathVariable Long user_id) {
        List<TestHistorial> testHistorials = testService.getTestHistorial(user_id);

        return new ResponseEntity<>(testHistorials, HttpStatus.OK);
    }

    @GetMapping("/list_test_docente/{user_id}")
    ResponseEntity<List<TestHistorialDocente>> getHistorialTestForDocente(@PathVariable Long user_id) {
        List<TestHistorialDocente> testHistorialsDocente = testService.getTestHistorialDocente(user_id);

        return new ResponseEntity<>(testHistorialsDocente, HttpStatus.OK);
    }

    @GetMapping("/reports_info/{user_id}")
    ResponseEntity<List<TotalTestForReports>> getInfoForReports(@PathVariable Long user_id) {
        List<TotalTestForReports> listTestForReports = testService.getTestforReports(user_id);

        return new ResponseEntity<>(listTestForReports, HttpStatus.OK);
    }

    @PutMapping("/{test_id}")
    ResponseEntity<?> changeFavorite(@PathVariable Long test_id) {
        testService.changeFavorite(test_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

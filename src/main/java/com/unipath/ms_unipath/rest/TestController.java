package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.services.TestService;
import com.unipath.ms_unipath.repositories.TestRepository;
import com.unipath.ms_unipath.rest.resources.test.TestHistorial;
import com.unipath.ms_unipath.rest.resources.test.TestRequest;
import com.unipath.ms_unipath.rest.resources.test.TestResource;
import com.unipath.ms_unipath.rest.resources.test.TestResponse;
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

    @PutMapping("/{test_id}")
    ResponseEntity<?> changeFavorite(@PathVariable Long test_id) {
        testService.changeFavorite(test_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

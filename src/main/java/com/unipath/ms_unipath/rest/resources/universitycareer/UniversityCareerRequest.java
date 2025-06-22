package com.unipath.ms_unipath.rest.resources.universitycareer;

import com.mysql.cj.exceptions.StreamingNotifiable;

public record UniversityCareerRequest(String logoUrl, String universityName, String color, String universityDescription, String universityImageUrl, String websiteUrl) {
}

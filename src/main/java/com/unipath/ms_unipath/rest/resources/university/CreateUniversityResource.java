package com.unipath.ms_unipath.rest.resources.university;

import com.unipath.ms_unipath.domain.model.entities.Career;

public record CreateUniversityResource(String name, String logoUrl, String imageUrl, String description, String color) {
}

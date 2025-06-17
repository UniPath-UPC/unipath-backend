package com.unipath.ms_unipath.shared.domain.model;


import com.unipath.ms_unipath.shared.domain.exceptions.BadRequestException;

public class StaticClass {
    public static final int PAGE_INDEX_OFFSET = 1;
    public static void validatePaginationValues(int page, int size) {
        if (page <= 0)
            throw new BadRequestException("The page number must be greater than zero");

        if (size <= 0)
            throw new BadRequestException("The size must be greater than 0");
    }
}

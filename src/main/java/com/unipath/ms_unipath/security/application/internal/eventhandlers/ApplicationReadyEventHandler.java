package com.unipath.ms_unipath.security.application.internal.eventhandlers;

import com.unipath.ms_unipath.security.domain.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ApplicationReadyEventHandler {
    private final RoleService roleService;
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(RoleService roleService) {
        this.roleService = roleService;
    }

}

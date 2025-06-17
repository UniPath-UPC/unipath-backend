package com.unipath.ms_unipath.security.infrastructure.authorization.sfs.pipeline;

import com.unipath.ms_unipath.shared.domain.exceptions.AuthenticatedException;
import com.unipath.ms_unipath.shared.domain.model.exceptions.AuthenticationError;
import com.unipath.ms_unipath.shared.domain.model.exceptions.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Locale;

@Component
public class UnauthorizedRequestHandlerEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(UnauthorizedRequestHandlerEntryPoint.class);


    public UnauthorizedRequestHandlerEntryPoint() {}

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException, ServletException {


        LOGGER.error("Unauthorized request: {}", authenticationException.getMessage());

        Locale locale = request.getLocale();

        AuthenticationError error = new AuthenticationError(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized access"
        );

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(new ErrorResponse(Collections.singletonList(error))));
    }
}

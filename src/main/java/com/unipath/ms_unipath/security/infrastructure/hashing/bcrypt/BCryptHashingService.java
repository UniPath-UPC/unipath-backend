package com.unipath.ms_unipath.security.infrastructure.hashing.bcrypt;

import com.unipath.ms_unipath.security.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}

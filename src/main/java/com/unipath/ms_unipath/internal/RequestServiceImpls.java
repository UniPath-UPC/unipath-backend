package com.unipath.ms_unipath.internal;

import com.unipath.ms_unipath.domain.model.entities.Request;
import com.unipath.ms_unipath.domain.services.RequestService;
import com.unipath.ms_unipath.repositories.RequestRepository;
import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResource;
import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResponse;
import com.unipath.ms_unipath.security.application.internal.outboundservices.hashing.HashingService;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories.UserRepository;
import com.unipath.ms_unipath.shared.domain.exceptions.AuthenticatedException;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class RequestServiceImpls implements RequestService {

    public RequestRepository requestRepository;
    public UserRepository userRepository;
    private final HashingService hashingService;
    private final JavaMailSender mailSender;

    @Autowired
    public RequestServiceImpls(RequestRepository requestRepository, UserRepository userRepository, HashingService hashingService, JavaMailSender mailSender) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.mailSender = mailSender;
    }

    @Override
    public Optional<ValidationCodeResponse> createRequestCode (String email){
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new NotFoundException("Not found user with that email");

        Random random = new Random();
        int numeroAleatorio = 100000 + random.nextInt(900000);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Código de verificación");

            String htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset='UTF-8'>
                  <style>
                    /* --- Reset básico para e-mails --- */
                    body, table, td, a { font-family: Arial, sans-serif; }
                    body { margin:0; padding:0; background:#f4f4f4; }
                    .container {
                      max-width:600px; margin:0 auto; background:#ffffff;
                      padding:24px; border-radius:8px; box-shadow:0 0 10px rgba(0,0,0,0.08);
                    }
                    h2 { color:#2c3e50; margin-top:0; }
                    .code-box {
                      font-size:32px; font-weight:bold; color:#2196f3;
                      letter-spacing:4px; border:2px dashed #2196f3;
                      padding:12px 24px; border-radius:6px; display:inline-block;
                      background:#e3f2fd; margin:20px 0;
                    }
                    p { color:#333; line-height:1.5em; }
                    .footer { font-size:12px; color:#777; margin-top:32px; }
                  </style>
                </head>
                <body>
                  <div class='container'>
                    <h2>¡Hola!</h2>
                    <p>Hemos recibido una solicitud para verificar tu identidad.</p>
                
                    <p>Introduce el siguiente código en el sistema para continuar:</p>
                
                    <div class='code-box'>%s</div>
                
                    <p>Si no solicitaste este código, ignora este mensaje.</p>
                
                    <p class='footer'>
                      Este código expirará en <strong>10&nbsp;minutos</strong>.<br>
                      © 2025 Tu Compañía – Todos los derechos reservados
                    </p>
                  </div>
                </body>
                </html>
                """.formatted(numeroAleatorio);

            helper.setText(htmlContent, true); // true para indicar que es HTML
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }

        //SimpleMailMessage message = new SimpleMailMessage();
        //message.setTo(email);
        //message.setSubject("Código de verificación");
        //message.setText("Tu código de verificación es: " + numeroAleatorio);
        //mailSender.send(message);
        
        Request newRequest = new Request(
                user,
                hashingService.encode(String.valueOf(numeroAleatorio)),
                LocalDateTime.now(),
                0
        );

        requestRepository.save(newRequest);

        return Optional.of(new ValidationCodeResponse(user.getId(), newRequest.getId()));
    }

    @Override
    public Integer validateRequest(ValidationCodeResource validationCodeResource){
        Request request = requestRepository.findById(validationCodeResource.id_request())
                .orElseThrow(() -> new NotFoundException("Not found request with id: "+ validationCodeResource.id_request()));

        if (hashingService.matches(validationCodeResource.code(), request.getValidation_code())) {
            request.setValidation(1);
            requestRepository.save(request);
            return 1;
        }
        else return 0;

    }
}

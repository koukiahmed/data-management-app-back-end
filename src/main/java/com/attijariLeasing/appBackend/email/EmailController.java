package com.attijariLeasing.appBackend.email;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public Response sendMail(@RequestBody Email email){
        return emailService.sendEmail(email);
    }
}




package com.disney.DuoDisney.service;

// @author aduo

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
 void sendWelcomeEmailTo(String to);
}

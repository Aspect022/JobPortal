package org.example.jobportal.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jobportal.Model.User;
import org.example.jobportal.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public OAuthSuccessHandler(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        // Extract user details
        String email = oauthUser.getAttribute("email");  // Google provides email
        String username = oauthUser.getAttribute("login");  // GitHub provides login name

        String identifier = (email != null) ? email : username;  // Use email if available, otherwise use GitHub username

        if (identifier == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unable to retrieve user info");
            return;
        }

        // Check if user exists
        if (!userRepository.findByUsername(identifier).isPresent()) {
            User newUser = new User();
            newUser.setUsername(identifier);
            newUser.setPassword("OAUTH");  // Dummy password, as OAuth users don't need one
            userRepository.save(newUser);
        }

        // Generate JWT
        String jwt = jwtUtil.generateToken(identifier);

        // Send token in response
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + jwt + "\"}");
    }
}


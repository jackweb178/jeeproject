package cours.jee.m1gl.api.controlleur;

import cours.jee.m1gl.api.config.JwtTokenUtil;
import cours.jee.m1gl.api.model.ErrorResponse;
import cours.jee.m1gl.api.model.JwtRequest;
import cours.jee.m1gl.api.model.ResponseJwt;
import cours.jee.m1gl.api.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest authenticationRequest) {

        final UserDetails details = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenUtil.generateToken(details);
            if (details != null)
                return ResponseEntity.ok(new ResponseJwt(jwt, details.getUsername(), details.getAuthorities()));
            return ResponseEntity.ok(new ErrorResponse("INVALID_CREDENTIALS"));
        } catch (DisabledException e) {
            return ResponseEntity.ok(new ErrorResponse("USER_DISABLED"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new ErrorResponse("INVALID_CREDENTIALS"));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @GetMapping("/test")
    public ResponseEntity<?> authenticateUser() {
        return ResponseEntity.ok(new ErrorResponse("Hello"));
    }
}
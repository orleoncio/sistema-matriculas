package tjw.sistema_matricula.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterCreateAuditor implements AuditorAware<String> {

    private static final Logger logger = LoggerFactory.getLogger(RegisterCreateAuditor.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Auditor: no authentication available");
            return Optional.empty();
        }

        if (!authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            logger.debug("Auditor: not authenticated or anonymous (principal = {})", authentication.getPrincipal());
            return Optional.empty();
        }

        String username = authentication.getName();
        logger.debug("Auditor: returning username = {}", username);
        return Optional.of(username);
    }
}

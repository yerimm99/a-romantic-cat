package aromanticcat.umcproject.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    public static String getLoginUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new IllegalStateException("Principal is not an instance of UserDetails");
        }

        return ((UserDetails) principal).getUsername();
    }
}

package ru.job4j.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.model.User;
import ru.job4j.service.TokenizerService;
import ru.job4j.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Authorization filter
 */
@Component("authFilter")
public class AuthFilter implements Filter {

    @Autowired
    private TokenizerService tokenizerService;

    @Autowired
    private UserService userService;

    private List<String> urlPatterns = Arrays.asList("/postad", "/editad");

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String auth = request.getHeader("Authorization");
        for (String urlPattern : urlPatterns) {
            if (request.getRequestURI().contains(urlPattern) && auth != null) {
                String email = tokenizerService.codeEncodeToken(auth);
                User user = userService.findByEmail(email);
                if (user.isEmpty()) {
//                    throw new AuthenticationException(
//                        String.format("User: %s is unknown.", user.getEmail()));
                    System.out.println(String.format("User: %s is unknown.", user.getEmail()));
                }
                req.setAttribute("user", user);
                break;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        //empty method
    }

    @Override
    public void destroy() {
        //empty method
    }
}

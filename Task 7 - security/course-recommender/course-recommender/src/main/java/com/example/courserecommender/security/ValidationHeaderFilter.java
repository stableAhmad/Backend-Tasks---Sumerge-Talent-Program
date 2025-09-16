package com.example.courserecommender.security;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import java.io.IOException;
import org.springframework.stereotype.Component;

/*

--------------- MY NOTES -----------------

Filter interface
    - Comes from Java EE / Jakarta Servlet API (javax.servlet.Filter or jakarta.servlet.Filter).
    - gotta implement void doFilter()
    - not specific to Spring, but works with any web app 
    - drawbacks:
        - must handle filter lifecycle manually
        - can be invoked multiple times per request in some scenarios (e.g., forwards, includes)
    
OncePerRequestFilter 
    - Abstarct class that implements Filter for you
    - Spring-specific abstract class
    - must implement its abstract method doFilterInternal()
    - ensures filter is executed only once per request
    - its the Filter implementation that Spring uses internally for its own filters


filter done 

*/

@Component
public class ValidationHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // this is printed each time the filter is invoked
        System.out.println("just making sure that the filter chain is working fine");

        System.out.println("=== Incoming Request ===");
        System.out.println("Method: " + request.getMethod());
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Headers:");
        request.getHeaderNames().asIterator().forEachRemaining(
                name -> System.out.println("  " + name + ": " + request.getHeader(name)));

        String header = request.getHeader("x-validation-report");
        if (header == null || !"true".equalsIgnoreCase(header)) {
            // give the proper response
            // HttpServletResposen is an object ran by the servlet container, for example
            // Tomcat
            // ... it represents the response that will be sent back to the client
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain");
            response.getWriter().write("Forbidden: missing or invalid x-validation-report header");
        } else {
            // request is good to go
            filterChain.doFilter(request, response); // continue if header is valid
        }
    }
}

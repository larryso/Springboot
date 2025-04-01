package com.larry.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Why CORS error there in the first place?
 *
 * The error stems from a security mechanism that browsers implement called the same-origin policy.
 *
 * The same-origin policy fights one of the most common cyber attacks: cross-site request forgery (a malicious website attempts to take advantage of the browser's cookie storage system)
 * For example, when you are accessing website: facebook.com, a malicious website may guide you click on a popup and opening evil-site.com, the evil-site will use your cookie storage and
 * gains authenticated access to your facebook.com, then your account has been successfully hacked.
 *
 * Then what is CORS (cross origin resource sharing)
 * CORS is a mechanism that allow a web page to access restricted resources from a server on a different domain.
 * For example, the front end javaScript code server from http://domain-a.come uses fetch() to make a request for https://domain-b.com/data.json
 *
 * For security reasons, browser restrict cross-origin http requests initialed from scripts, then how browser achieve this?
 * Browser will check the Access-Control_Allow-Origin value from header that respond from server (in our case, backend server), if the frontend domain not match this value, browser will block
 * the API request with CORS policy error.
 */
@Component
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        if(HttpMethod.OPTIONS.name().equals(req.getMethod())){
            res.setStatus(HttpStatus.ACCEPTED.value());
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

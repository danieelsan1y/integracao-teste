package com.teste.integracao.inttest.teste;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object
    ) {
        logger.info("Preparing the server to receive the request.");
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            ModelAndView model
    ) {

    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception exception
    ) {

        final String content = String.format(
                "[ path: { %s }, method: { %s }, status: { %d } ]",
                request.getRequestURI(),
                request.getMethod(),
                response.getStatus()
        );

        logger.info(content);
    }
}

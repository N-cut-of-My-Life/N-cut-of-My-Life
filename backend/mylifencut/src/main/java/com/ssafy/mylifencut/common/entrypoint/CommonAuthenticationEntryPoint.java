package com.ssafy.mylifencut.common.entrypoint;

import static com.ssafy.mylifencut.user.UserConstant.INVALID_ACCESS_TOKEN_ERROR_MESSAGE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.info(authException.getLocalizedMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setContentType("text/json;charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        response.getWriter().write(new ObjectMapper().writeValueAsString(
                BaseResponse.from(false, INVALID_ACCESS_TOKEN_ERROR_MESSAGE)
        ));

    }
}

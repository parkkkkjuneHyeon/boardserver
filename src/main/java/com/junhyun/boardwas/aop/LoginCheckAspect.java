package com.junhyun.boardwas.aop;

import com.junhyun.boardwas.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Log4j2
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class LoginCheckAspect {
    @Around("@annotation(com.junhyun.boardwas.aop.LoginCheck) && @annotation(loginCheck)")
    public Object adminLoginCheck(ProceedingJoinPoint joinPoint, LoginCheck loginCheck) throws Throwable {
        log.info("LoginCheckAspect 진입");
        HttpSession session = (HttpSession) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        String email = null;
        int emailIndex = 0;
        String userType = loginCheck.type().toString();
        switch (userType) {
            case "ADMIN": {
                email = SessionUtil.getLoginAdminEmail(session);
                break;
            }
            case "USER": {
                email = SessionUtil.getLoginMemberEmail(session);
                break;
            }
        }
        if (email == null) {
            log.debug(joinPoint.toString()+"accountName : " + email);
            throw  new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인한 email값을 확인 해주세요.") {};
        }
        Object[] modifiedArgs = joinPoint.getArgs();
        if (modifiedArgs != null)
            modifiedArgs[emailIndex] = email;
        log.info("LoginCheckAspect 종료");
        return joinPoint.proceed(modifiedArgs);
    }
}

package com.junhyun.boardwas.utils;


import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_MEMBER_EMAIL = "LOGIN_MEMBER_EMAIL";
    private static final String LOGIN_ADMIN_EMAIL = "LOGIN_ADMIN_EMAIL";

    private SessionUtil() {}

    public static void setLoginAdminEmail(HttpSession session, String email) {
        session.setAttribute(LOGIN_ADMIN_EMAIL, email);
    }

    public static void setLoginMemberEmail(HttpSession session, String email) {
        session.setAttribute(LOGIN_MEMBER_EMAIL, email);
    }

    public static String getLoginEmail(HttpSession session) {
        String email = getLoginMemberEmail(session);
        if (email == null) {
            email = SessionUtil.getLoginAdminEmail(session);
        }else {
            throw new RuntimeException("Login Error ! 유저 정보가 없거나 지원되지 않는 유저입니다.");
        }
        return email;
    }

    public static String getLoginMemberEmail(HttpSession session) {
        return (String) session.getAttribute(LOGIN_MEMBER_EMAIL);
    }

    public static String getLoginAdminEmail(HttpSession session) {
        return (String) session.getAttribute(LOGIN_ADMIN_EMAIL);
    }

    public static void clear(HttpSession session) {
        session.invalidate();
    }

}

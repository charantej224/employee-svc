package com.employee.service.config;

/**
 * Application constants.
 */
public final class Constants {

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String MESSAGE_101 = "101";
    public static final String MESSAGE_102 = "102";
    public static final String MESSAGE_103 = "103";
    public static final String MESSAGE_104 = "104";
    public static final String MESSAGE_105 = "105";

    public static final String MESSAGE_200 = "200";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private Constants() {
    }
}

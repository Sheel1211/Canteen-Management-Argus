package com.argus.cms.constants;

public class Constants {

    public static final String JWT_SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
    public static final String JWT_ALGORITHM = "HS256";
    public static final int JWT_EXPIRATION_TIME = 360000000;

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}


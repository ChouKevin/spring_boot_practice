package com.example.demo.Auth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    /** all */
    ROOT(-1),
    SUPER_USER(Integer.MAX_VALUE),
    /** Read Update Delete company/client */
    MANAGER(0b0111),
    /** Create Read company/client */
    OPERATOR(0b1100),
    ;

    final int code;

    public int code() { return code; }

    public static Permission fromCode(int code) {
        for (Permission role : Permission.values()) {
            if (role.code == code) return role;
        }
        throw new RuntimeException("undefinded permission code");
    }
}

package com.guanwx.base.switchs;

import lombok.Getter;

/**
 * @Author: guanwx
 * @CreateTime: 2026/10/5 星期一 17:41
 * @Description:
 * @Version: 1.0
 */

@Getter
public enum ProjectEnum {

    CHINESE(1, "语文"),
    MATH(2, "数学"),
    ENGLISH(3, "英语");

    private final int code;
    private final String displayName;

    ProjectEnum(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

}

package com.zipe.enums;

public enum SheduleJobStatusEmun {
    DELETE(0, "DELETE"),
    START(1, "START"),
    SUSPEND(2, "SUSPEND"),
    RESUME(3, "RESUME"),
    CREATE(4, "CREATE");

    public int status;
    public String desc;

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    SheduleJobStatusEmun(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

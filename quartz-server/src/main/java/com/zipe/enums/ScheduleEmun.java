package com.zipe.enums;

import org.quartz.*;

public enum ScheduleEmun {
    NOW(0) {
        public SimpleScheduleBuilder setCycle(int interval, int repeatCount) {
            return SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow();
        }
    },
    SECOND(1) {
        public SimpleScheduleBuilder setCycle(int interval, int repeatCount) {
            return SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(interval).withRepeatCount(repeatCount);
        }
    },
    MINUTE(2) {
        public SimpleScheduleBuilder setCycle(int interval, int repeatCount) {
            return SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(interval).withRepeatCount(repeatCount);
        }
    },
    HOUR(3) {
        public SimpleScheduleBuilder setCycle(int interval, int repeatCount) {
            return SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(interval).withRepeatCount(repeatCount);
        }
    },
    DAY(4) {
        public DailyTimeIntervalScheduleBuilder setCycle(int interval, int repeatCount) {
            return DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withInterval(interval, DateBuilder.IntervalUnit.DAY).withRepeatCount(repeatCount);
        }
    },
    WEEK(5) {
        public CalendarIntervalScheduleBuilder setCycle(int interval, int repeatCount) {
            return CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInWeeks(interval);
        }
    },
    MONTH(6) {
        public CalendarIntervalScheduleBuilder setCycle(int interval, int repeatCount) {
            return CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMonths(interval);
        }
    },
    YEAR(7) {
        public CalendarIntervalScheduleBuilder setCycle(int interval, int repeatCount) {
            return CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInYears(interval);
        }
    };

    public abstract ScheduleBuilder setCycle(int interval, int repeatCount);

    public int timeUnit;

    public int getTimeUnit() {
        return timeUnit;
    }

    ScheduleEmun(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public static ScheduleEmun getTimeUnit(int timeUnit) {
        for (ScheduleEmun scheduleEmun : ScheduleEmun.values()) {
            if (scheduleEmun.getTimeUnit() == timeUnit) {
                return scheduleEmun;
            }
        }
        return null;
    }
}

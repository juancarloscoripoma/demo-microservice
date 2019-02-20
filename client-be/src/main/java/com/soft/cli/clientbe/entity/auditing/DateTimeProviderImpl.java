package com.soft.cli.clientbe.entity.auditing;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeProviderImpl implements DateTimeProvider {

    @Override
    public Calendar getNow(){
        return new GregorianCalendar();
    }
}

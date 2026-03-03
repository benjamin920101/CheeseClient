/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StdDateFormat
extends DateFormat {
    protected static final String PATTERN_PLAIN_STR = "\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d";
    protected static final Pattern PATTERN_PLAIN = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d");
    protected static final Pattern PATTERN_ISO8601;
    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    protected static final String[] ALL_FORMATS;
    protected static final TimeZone DEFAULT_TIMEZONE;
    protected static final Locale DEFAULT_LOCALE;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    protected static final DateFormat DATE_FORMAT_ISO8601;
    public static final StdDateFormat instance;
    protected static final Calendar CALENDAR;
    protected transient TimeZone _timezone;
    protected final Locale _locale;
    protected Boolean _lenient;
    private transient Calendar _calendar;
    private transient DateFormat _formatRFC1123;
    private boolean _tzSerializedWithColon = false;

    public StdDateFormat() {
        this._locale = DEFAULT_LOCALE;
    }

    @Deprecated
    public StdDateFormat(TimeZone tz2, Locale loc) {
        this._timezone = tz2;
        this._locale = loc;
    }

    protected StdDateFormat(TimeZone tz2, Locale loc, Boolean lenient) {
        this(tz2, loc, lenient, false);
    }

    protected StdDateFormat(TimeZone tz2, Locale loc, Boolean lenient, boolean formatTzOffsetWithColon) {
        this._timezone = tz2;
        this._locale = loc;
        this._lenient = lenient;
        this._tzSerializedWithColon = formatTzOffsetWithColon;
    }

    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }

    public StdDateFormat withTimeZone(TimeZone tz2) {
        if (tz2 == null) {
            tz2 = DEFAULT_TIMEZONE;
        }
        if (tz2 == this._timezone || tz2.equals(this._timezone)) {
            return this;
        }
        return new StdDateFormat(tz2, this._locale, this._lenient, this._tzSerializedWithColon);
    }

    public StdDateFormat withLocale(Locale loc) {
        if (loc.equals(this._locale)) {
            return this;
        }
        return new StdDateFormat(this._timezone, loc, this._lenient, this._tzSerializedWithColon);
    }

    public StdDateFormat withLenient(Boolean b2) {
        if (StdDateFormat._equals(b2, this._lenient)) {
            return this;
        }
        return new StdDateFormat(this._timezone, this._locale, b2, this._tzSerializedWithColon);
    }

    public StdDateFormat withColonInTimeZone(boolean b2) {
        if (this._tzSerializedWithColon == b2) {
            return this;
        }
        return new StdDateFormat(this._timezone, this._locale, this._lenient, b2);
    }

    @Override
    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale, this._lenient, this._tzSerializedWithColon);
    }

    @Deprecated
    public static DateFormat getISO8601Format(TimeZone tz2, Locale loc) {
        return StdDateFormat._cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, tz2, loc, null);
    }

    @Deprecated
    public static DateFormat getRFC1123Format(TimeZone tz2, Locale loc) {
        return StdDateFormat._cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, tz2, loc, null);
    }

    @Override
    public TimeZone getTimeZone() {
        return this._timezone;
    }

    @Override
    public void setTimeZone(TimeZone tz2) {
        if (!tz2.equals(this._timezone)) {
            this._clearFormats();
            this._timezone = tz2;
        }
    }

    @Override
    public void setLenient(boolean enabled) {
        Boolean newValue = enabled;
        if (!StdDateFormat._equals(newValue, this._lenient)) {
            this._lenient = newValue;
            this._clearFormats();
        }
    }

    @Override
    public boolean isLenient() {
        return this._lenient == null || this._lenient != false;
    }

    public boolean isColonIncludedInTimeZone() {
        return this._tzSerializedWithColon;
    }

    @Override
    public Date parse(String dateStr) throws ParseException {
        ParsePosition pos;
        Date dt2 = this._parseDate(dateStr = dateStr.trim(), pos = new ParsePosition(0));
        if (dt2 != null) {
            return dt2;
        }
        StringBuilder sb2 = new StringBuilder();
        for (String f2 : ALL_FORMATS) {
            if (sb2.length() > 0) {
                sb2.append("\", \"");
            } else {
                sb2.append('\"');
            }
            sb2.append(f2);
        }
        sb2.append('\"');
        throw new ParseException(String.format("Cannot parse date \"%s\": not compatible with any of standard forms (%s)", dateStr, sb2.toString()), pos.getErrorIndex());
    }

    @Override
    public Date parse(String dateStr, ParsePosition pos) {
        try {
            return this._parseDate(dateStr, pos);
        }
        catch (ParseException parseException) {
            return null;
        }
    }

    protected Date _parseDate(String dateStr, ParsePosition pos) throws ParseException {
        char ch;
        if (this.looksLikeISO8601(dateStr)) {
            return this.parseAsISO8601(dateStr, pos);
        }
        int i2 = dateStr.length();
        while (--i2 >= 0 && ((ch = dateStr.charAt(i2)) >= '0' && ch <= '9' || i2 <= 0 && ch == '-')) {
        }
        if (i2 < 0 && (dateStr.charAt(0) == '-' || NumberInput.inLongRange(dateStr, false))) {
            return this._parseDateFromLong(dateStr, pos);
        }
        return this.parseAsRFC1123(dateStr, pos);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        TimeZone tz2 = this._timezone;
        if (tz2 == null) {
            tz2 = DEFAULT_TIMEZONE;
        }
        this._format(tz2, this._locale, date, toAppendTo);
        return toAppendTo;
    }

    protected void _format(TimeZone tz2, Locale loc, Date date, StringBuffer buffer) {
        Calendar cal = this._getCalendar(tz2);
        cal.setTime(date);
        int year = cal.get(1);
        if (cal.get(0) == 0) {
            this._formatBCEYear(buffer, year);
        } else {
            if (year > 9999) {
                buffer.append('+');
            }
            StdDateFormat.pad4(buffer, year);
        }
        buffer.append('-');
        StdDateFormat.pad2(buffer, cal.get(2) + 1);
        buffer.append('-');
        StdDateFormat.pad2(buffer, cal.get(5));
        buffer.append('T');
        StdDateFormat.pad2(buffer, cal.get(11));
        buffer.append(':');
        StdDateFormat.pad2(buffer, cal.get(12));
        buffer.append(':');
        StdDateFormat.pad2(buffer, cal.get(13));
        buffer.append('.');
        StdDateFormat.pad3(buffer, cal.get(14));
        int offset = tz2.getOffset(cal.getTimeInMillis());
        if (offset != 0) {
            int hours = Math.abs(offset / 60000 / 60);
            int minutes = Math.abs(offset / 60000 % 60);
            buffer.append(offset < 0 ? (char)'-' : '+');
            StdDateFormat.pad2(buffer, hours);
            if (this._tzSerializedWithColon) {
                buffer.append(':');
            }
            StdDateFormat.pad2(buffer, minutes);
        } else if (this._tzSerializedWithColon) {
            buffer.append("+00:00");
        } else {
            buffer.append("+0000");
        }
    }

    protected void _formatBCEYear(StringBuffer buffer, int bceYearNoSign) {
        if (bceYearNoSign == 1) {
            buffer.append("+0000");
            return;
        }
        int isoYear = bceYearNoSign - 1;
        buffer.append('-');
        StdDateFormat.pad4(buffer, isoYear);
    }

    private static void pad2(StringBuffer buffer, int value) {
        int tens = value / 10;
        if (tens == 0) {
            buffer.append('0');
        } else {
            buffer.append((char)(48 + tens));
            value -= 10 * tens;
        }
        buffer.append((char)(48 + value));
    }

    private static void pad3(StringBuffer buffer, int value) {
        int h2 = value / 100;
        if (h2 == 0) {
            buffer.append('0');
        } else {
            buffer.append((char)(48 + h2));
            value -= h2 * 100;
        }
        StdDateFormat.pad2(buffer, value);
    }

    private static void pad4(StringBuffer buffer, int value) {
        int h2 = value / 100;
        if (h2 == 0) {
            buffer.append('0').append('0');
        } else {
            if (h2 > 99) {
                buffer.append(h2);
            } else {
                StdDateFormat.pad2(buffer, h2);
            }
            value -= 100 * h2;
        }
        StdDateFormat.pad2(buffer, value);
    }

    public String toString() {
        return String.format("DateFormat %s: (timezone: %s, locale: %s, lenient: %s)", this.getClass().getName(), this._timezone, this._locale, this._lenient);
    }

    public String toPattern() {
        StringBuilder sb2 = new StringBuilder(100);
        sb2.append("[one of: '").append(DATE_FORMAT_STR_ISO8601).append("', '").append(DATE_FORMAT_STR_RFC1123).append("' (");
        sb2.append(Boolean.FALSE.equals(this._lenient) ? "strict" : "lenient").append(")]");
        return sb2.toString();
    }

    @Override
    public boolean equals(Object o2) {
        return o2 == this;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    protected boolean looksLikeISO8601(String dateStr) {
        return dateStr.length() >= 7 && Character.isDigit(dateStr.charAt(0)) && Character.isDigit(dateStr.charAt(3)) && dateStr.charAt(4) == '-' && Character.isDigit(dateStr.charAt(5));
    }

    private Date _parseDateFromLong(String longStr, ParsePosition pos) throws ParseException {
        long ts2;
        try {
            ts2 = NumberInput.parseLong(longStr);
        }
        catch (NumberFormatException e2) {
            throw new ParseException(String.format("Timestamp value %s out of 64-bit value range", longStr), pos.getErrorIndex());
        }
        return new Date(ts2);
    }

    protected Date parseAsISO8601(String dateStr, ParsePosition pos) throws ParseException {
        try {
            return this._parseAsISO8601(dateStr, pos);
        }
        catch (IllegalArgumentException e2) {
            throw new ParseException(String.format("Cannot parse date \"%s\", problem: %s", dateStr, e2.getMessage()), pos.getErrorIndex());
        }
    }

    protected Date _parseAsISO8601(String dateStr, ParsePosition bogus) throws IllegalArgumentException, ParseException {
        String formatStr;
        int totalLen = dateStr.length();
        TimeZone tz2 = DEFAULT_TIMEZONE;
        if (this._timezone != null && 'Z' != dateStr.charAt(totalLen - 1)) {
            tz2 = this._timezone;
        }
        Calendar cal = this._getCalendar(tz2);
        cal.clear();
        if (totalLen <= 10) {
            Matcher m2 = PATTERN_PLAIN.matcher(dateStr);
            if (m2.matches()) {
                int year = StdDateFormat._parse4D(dateStr, 0);
                int month = StdDateFormat._parse2D(dateStr, 5) - 1;
                int day = StdDateFormat._parse2D(dateStr, 8);
                cal.set(year, month, day, 0, 0, 0);
                cal.set(14, 0);
                return cal.getTime();
            }
            formatStr = DATE_FORMAT_STR_PLAIN;
        } else {
            Matcher m3 = PATTERN_ISO8601.matcher(dateStr);
            if (m3.matches()) {
                int start = m3.start(2);
                int end = m3.end(2);
                int len = end - start;
                if (len > 1) {
                    int offsetSecs = StdDateFormat._parse2D(dateStr, start + 1) * 3600;
                    if (len >= 5) {
                        offsetSecs += StdDateFormat._parse2D(dateStr, end - 2) * 60;
                    }
                    offsetSecs = dateStr.charAt(start) == '-' ? (offsetSecs *= -1000) : (offsetSecs *= 1000);
                    cal.set(15, offsetSecs);
                    cal.set(16, 0);
                }
                int year = StdDateFormat._parse4D(dateStr, 0);
                int month = StdDateFormat._parse2D(dateStr, 5) - 1;
                int day = StdDateFormat._parse2D(dateStr, 8);
                int hour = StdDateFormat._parse2D(dateStr, 11);
                int minute = StdDateFormat._parse2D(dateStr, 14);
                int seconds = totalLen > 16 && dateStr.charAt(16) == ':' ? StdDateFormat._parse2D(dateStr, 17) : 0;
                cal.set(year, month, day, hour, minute, seconds);
                start = m3.start(1) + 1;
                end = m3.end(1);
                int msecs = 0;
                if (start >= end) {
                    cal.set(14, 0);
                } else {
                    msecs = 0;
                    int fractLen = end - start;
                    switch (fractLen) {
                        default: {
                            if (fractLen > 9) {
                                throw new ParseException(String.format("Cannot parse date \"%s\": invalid fractional seconds '%s'; can use at most 9 digits", dateStr, m3.group(1).substring(1)), start);
                            }
                        }
                        case 3: {
                            msecs += dateStr.charAt(start + 2) - 48;
                        }
                        case 2: {
                            msecs += 10 * (dateStr.charAt(start + 1) - 48);
                        }
                        case 1: {
                            msecs += 100 * (dateStr.charAt(start) - 48);
                        }
                        case 0: 
                    }
                    cal.set(14, msecs);
                }
                return cal.getTime();
            }
            formatStr = DATE_FORMAT_STR_ISO8601;
        }
        throw new ParseException(String.format("Cannot parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)", dateStr, formatStr, this._lenient), 0);
    }

    private static int _parse4D(String str, int index) {
        return 1000 * (str.charAt(index) - 48) + 100 * (str.charAt(index + 1) - 48) + 10 * (str.charAt(index + 2) - 48) + (str.charAt(index + 3) - 48);
    }

    private static int _parse2D(String str, int index) {
        return 10 * (str.charAt(index) - 48) + (str.charAt(index + 1) - 48);
    }

    protected Date parseAsRFC1123(String dateStr, ParsePosition pos) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = StdDateFormat._cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, this._timezone, this._locale, this._lenient);
        }
        return this._formatRFC1123.parse(dateStr, pos);
    }

    private static final DateFormat _cloneFormat(DateFormat df2, String format, TimeZone tz2, Locale loc, Boolean lenient) {
        if (!loc.equals(DEFAULT_LOCALE)) {
            df2 = new SimpleDateFormat(format, loc);
            df2.setTimeZone(tz2 == null ? DEFAULT_TIMEZONE : tz2);
        } else {
            df2 = (DateFormat)df2.clone();
            if (tz2 != null) {
                df2.setTimeZone(tz2);
            }
        }
        if (lenient != null) {
            df2.setLenient(lenient);
        }
        return df2;
    }

    protected void _clearFormats() {
        this._formatRFC1123 = null;
    }

    protected Calendar _getCalendar(TimeZone tz2) {
        Calendar cal = this._calendar;
        if (cal == null) {
            this._calendar = cal = (Calendar)CALENDAR.clone();
        }
        if (!cal.getTimeZone().equals(tz2)) {
            cal.setTimeZone(tz2);
        }
        cal.setLenient(this.isLenient());
        return cal;
    }

    protected static <T> boolean _equals(T value1, T value2) {
        if (value1 == value2) {
            return true;
        }
        return value1 != null && value1.equals(value2);
    }

    static {
        Pattern p2 = null;
        try {
            p2 = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d(?:[:]\\d\\d)?(\\.\\d+)?(Z|[+-]\\d\\d(?:[:]?\\d\\d)?)?");
        }
        catch (Throwable t2) {
            throw new RuntimeException(t2);
        }
        PATTERN_ISO8601 = p2;
        ALL_FORMATS = new String[]{DATE_FORMAT_STR_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSS", DATE_FORMAT_STR_RFC1123, DATE_FORMAT_STR_PLAIN};
        DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
        DEFAULT_LOCALE = Locale.US;
        DATE_FORMAT_RFC1123 = new SimpleDateFormat(DATE_FORMAT_STR_RFC1123, DEFAULT_LOCALE);
        DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601 = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601, DEFAULT_LOCALE);
        DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
        instance = new StdDateFormat();
        CALENDAR = new GregorianCalendar(DEFAULT_TIMEZONE, DEFAULT_LOCALE);
    }
}


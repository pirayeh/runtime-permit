package ir.pirayeh1485.runtimepermit;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abolhassan
 * on 1/24/2018.
 */

public enum Permission {
    READ_CALENDAR(Manifest.permission.READ_CALENDAR, 113, "خواندن تقویم"),
    WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR, 114, "نوشتن در تقویم"),
    CAMERA(Manifest.permission.CAMERA, 115, "دوربین"),
    READ_CONTACTS(Manifest.permission.READ_CONTACTS, 116, "خواندن مخاطبین"),
    WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS, 117, "نوشتن مخاطبین"),
    GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS, 118, "گرفتن اکانت ها"),
    ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION, 119, "دسترسی به مختصات دقیق"),
    ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION, 120, "دسترسی به مختصات تقریبی"),
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO, 121, "ضبط صدا"),
    READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE, 122, "خواندن وضعیت تلفن"),
    @RequiresApi(api = Build.VERSION_CODES.O)
    READ_PHONE_NUMBERS(Manifest.permission.READ_PHONE_NUMBERS, 123, "خواندن وضعیت تلفن"),
    CALL_PHONE(Manifest.permission.CALL_PHONE, 124, "تماس"),
    @RequiresApi(api = Build.VERSION_CODES.O)
    ANSWER_PHONE_CALLS(Manifest.permission.ANSWER_PHONE_CALLS, 125, "پاسخ به تماس"),
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    READ_CALL_LOG(Manifest.permission.READ_CALL_LOG, 126, "خواندن تاریخچه تماس"),
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG, 127, "نوشتن در تاریخچه تماس"),
    ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL, 128, "اضافه کردن ایمیل صوتی"),
    USE_SIP(Manifest.permission.USE_SIP, 129, "استفاده از اس آی پی"),
    PROCESS_OUTGOING_CALLS(Manifest.permission.PROCESS_OUTGOING_CALLS, 128, "فرآیند تماسهای خروجی"),
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    BODY_SENSORS(Manifest.permission.BODY_SENSORS, 130, "کار با سنسورها"),
    SEND_SMS(Manifest.permission.SEND_SMS, 131, "فرستادن پیامک"),
    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS, 132, "دریافت پیامک"),
    RECEIVE_MMS(Manifest.permission.RECEIVE_MMS, 133, "دریافت پیامک"),
    READ_SMS(Manifest.permission.READ_SMS, 134, "خواندن mms"),
    RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH, 135, "RECEIVE_WAP_PUSH"),
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE, 136, "خواندن از کارت حافظه"),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE, 137, "نوشتن روی کارت حافظه");


    private final String name;
    private final String translate;
    private final int code;

    Permission(String name, int code, String translate) {
        this.name = name;
        this.code = code;
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static Permission getByName(String name) {
        Permission permission = null;
        for (Permission p : Permission.values()) {
            if (p.name.equals(name)) {
                permission = p;
            }
        }
        return permission;
    }

    public static Permission[] getPermissionsByName(List<String> names) {
        List<Permission> permissions = new ArrayList<>();
        for (Permission p : Permission.values()) {
            for (String name : names) {
                if (p.name.equals(name)) {
                    permissions.add(p);
                }
            }
        }
        return permissions.toArray(new Permission[permissions.size()]);
    }
}

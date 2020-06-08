package com.task.ecobike.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TranslationService {

    public Object toEntity(String line, Object o) {
        try {
            String[] arr = line.split("; ");
            Class aClass = o.getClass();
            List<Field> fields = getAllFields(new ArrayList<>(), aClass);

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("brand")) {
                    field.set(o, arr[0]);
                }
                if (field.getName().equals("wheelsSize")) {
                    field.set(o, Integer.parseInt(arr[1]));
                }
                if (field.getName().equals("maxSpeed")) {
                    field.set(o, Integer.parseInt(arr[1]));
                }
                if (field.getName().equals("gear")) {
                    field.set(o, Integer.parseInt(arr[2]));
                }
                if (field.getName().equals("weight")) {
                    if (line.startsWith("FOLDING BIKE")) {
                        field.set(o, Integer.parseInt(arr[3]));
                    } else {
                        field.set(o, Integer.parseInt(arr[2]));
                    }
                }
                if (field.getName().equals("lights")) {
                    if (line.startsWith("FOLDING BIKE")) {
                        field.set(o, Boolean.parseBoolean(arr[4]));
                    } else {
                        field.set(o, Boolean.parseBoolean(arr[3]));
                    }
                }
                if (field.getName().equals("batteryCapacity")) {
                    field.set(o, Integer.parseInt(arr[4]));
                }
                if (field.getName().equals("color")) {
                    field.set(o, arr[5]);
                }
                if (field.getName().equals("price")) {
                    field.set(o, Integer.parseInt(arr[6]));
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return o;
    }

    public List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}

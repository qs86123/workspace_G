package com.myself.edu.utils;

import java.lang.reflect.Field;

public enum ReflectionUtil {
	INS;
	ReflectionUtil () {}
    public <T> T getFieldValue(Object object,
                                      String fullName) throws IllegalAccessException {
        if(object == null || fullName == null){
        	return null;
        }
    	return getFieldValue(object, fullName, false);
    }

    public <T> T getFieldValue(Object object,
                                      String fieldName,
                                      boolean traceable) throws IllegalAccessException {
    	if(object == null || fieldName == null){
        	return null;
        }    	
    	Field field;
        String[] fieldNames = fieldName.split("\\.");
        for (String targetField : fieldNames) {
            field = searchField(object.getClass(), targetField, traceable);
            if (field == null)
                return null;

            object = getValue(object, field);
        }

        return (T) object;
    }

    private Field searchField(Class c, String targetField, boolean traceable) {
        do {
            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                if (f.getName().equals(targetField)) {
                    return f;
                }
            }
            c = c.getSuperclass();
            traceable = traceable && c != Object.class;
        } while (traceable);

        return null;
    }

    private <T> T getValue(Object target, Field field) throws IllegalAccessException {
        if (!field.isAccessible())
            field.setAccessible(true);
        return (T) field.get(target);
    }

    public boolean setFieldValue(Object target,
                                        String fieldName,
                                        Object value) throws IllegalAccessException {
    	if(target == null || fieldName == null || value == null){
        	return false;
        } 
    	return setFieldValue(target, fieldName, value, false);
    }

    public boolean setFieldValue(Object target,
                                        String fieldName,
                                        Object value,
                                        boolean traceable) throws IllegalAccessException {
    	if(target == null || fieldName == null || value == null){
        	return false;
        } 
    	Field field = searchField(target.getClass(), fieldName, traceable);
        if (field != null)
            return setValue(field, target, value);
        return false;
    }

    private boolean setValue(Field field, Object target, Object value) throws IllegalAccessException {
        if (!field.isAccessible())
            field.setAccessible(true);
        field.set(target, value);
        return true;
    }
}
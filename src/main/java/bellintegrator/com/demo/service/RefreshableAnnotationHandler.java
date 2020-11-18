package bellintegrator.com.demo.service;

import bellintegrator.com.demo.annotaion.Refreshable;

import java.lang.reflect.Field;

public class RefreshableAnnotationHandler {

    public static void RefreshableFieldsCopy(Class<?> currentClass, Object newObj, Object oldObj) throws IllegalAccessException {
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Refreshable.class) != null) {
                field.setAccessible(true);
                if (field.get(newObj) != null) {
                    field.set(oldObj, field.get(newObj));
                }
            }
        }
    }
}

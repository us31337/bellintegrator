package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.annotaion.Refreshable;
import bellintegrator.com.demo.service.RefresheableHandler;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class RefresheableHandlerImpl implements RefresheableHandler {

    @Override
    public void RefreshableFieldsCopy(Class<?> currentClass, Object newObj, Object oldObj) throws IllegalAccessException {
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

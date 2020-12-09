package bellintegrator.com.demo.service.impl;

import bellintegrator.com.demo.annotaion.Refreshable;
import bellintegrator.com.demo.service.NotNullFieldsEntityCopier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * {@inheritDoc}
 */
@Service
public class NotNullFieldsEntityCopierImpl<T> implements NotNullFieldsEntityCopier<T> {

    @Override
    public void updatableFieldsCopy(T newObj, T oldObj) throws IllegalAccessException {
        Field[] fields = newObj.getClass().getDeclaredFields();
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

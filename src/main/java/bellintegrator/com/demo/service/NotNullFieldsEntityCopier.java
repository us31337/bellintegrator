package bellintegrator.com.demo.service;

public interface NotNullFieldsEntityCopier<T> {
    public void updatableFieldsCopy(T newObj, T oldObj) throws IllegalAccessException;
}

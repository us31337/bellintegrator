package bellintegrator.com.demo.service;

public interface NotNullFieldsEntityCopier<T> {
    public void RefreshableFieldsCopy(T newObj, T oldObj) throws IllegalAccessException;
}

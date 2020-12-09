package bellintegrator.com.demo.service;

/**
 * Интерфейс копирования полей одного объекта в поля другого такого же класса
 *
 * @param <T> класс объектов
 */
public interface NotNullFieldsEntityCopier<T> {
    /**
     * Копирует значения полей одного объекта в такие же поля другого
     * @param newObj объект с новыми значениями в полях
     * @param oldObj объект с устаревшими значениями в полях
     * @throws IllegalAccessException
     */
    public void updatableFieldsCopy(T newObj, T oldObj) throws IllegalAccessException;
}

package org.bahmni.csv;

import java.lang.reflect.Field;

public class CSVRow<T extends CSVEntity> {
    private CSVColumns columns;
    private Class<T> entityClass;
    private Object id;

    public CSVRow(CSVColumns columns, Class<T> entityClass) {
        this.columns = columns;
        this.entityClass = entityClass;
    }

    public T getEntity(String[] aRow) throws IllegalAccessException, InstantiationException {
        if (aRow == null)
            return null;

        T entity = entityClass.newInstance();

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            columns.setValue(entity, field, aRow);
        }

        entity.originalRow(aRow);
        return entity;
    }
}

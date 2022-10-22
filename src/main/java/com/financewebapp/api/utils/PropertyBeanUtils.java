package com.financewebapp.api.utils;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.model.Entry;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class PropertyBeanUtils extends BeanUtilsBean {

    public void compareEntry(Entry entryDB, Entry entry) throws InvocationTargetException, IllegalAccessException {
        copyProperty(entryDB, "name", entry.getName());

        copyProperty(entryDB, "value", entry.getValue());

        copyProperty(entryDB, "month", entry.getMonth());

        copyProperty(entryDB, "year", entry.getYear());

        copyProperty(entryDB, "month", entry.getMonth());

        copyProperty(entryDB, "authorId", entry.getAuthorId());

        copyProperty(entryDB, "categoryId", entry.getCategoryId());
    }

    public void compareDebit(Debit debitDB, Debit debit) throws InvocationTargetException, IllegalAccessException {
        copyProperty(debitDB, "name", debit.getName());

        copyProperty(debitDB, "value", debit.getValue());

        copyProperty(debitDB, "month", debit.getMonth());

        copyProperty(debitDB, "year", debit.getYear());

        copyProperty(debitDB, "month", debit.getMonth());

        copyProperty(debitDB, "authorId", debit.getAuthorId());

        copyProperty(debitDB, "categoryId", debit.getCategoryId());
    }
    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value == null) return;
        super.copyProperty(dest, name, value);
    }
}

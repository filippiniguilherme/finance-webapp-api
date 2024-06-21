package com.financewebapp.api.utils;

import com.financewebapp.api.infrastructure.model.Debit;
import com.financewebapp.api.infrastructure.model.Entry;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class PropertyBeanUtils extends BeanUtilsBean {

    public void compareEntry(Entry entryDB, Entry entry) throws InvocationTargetException, IllegalAccessException {
        copyProperty(entryDB, "name", entry.getName());

        copyProperty(entryDB, "value", entry.getValue());

        copyProperty(entryDB, "dateCreated", entry.getDateCreated());

        copyProperty(entryDB, "year", entry.getYear());

        copyProperty(entryDB, "month", entry.getMonth());

        copyProperty(entryDB, "author", entry.getAuthor());

        copyProperty(entryDB, "category", entry.getCategory());
    }

    public void compareDebit(Debit debitDB, Debit debit) throws InvocationTargetException, IllegalAccessException {
        copyProperty(debitDB, "name", debit.getName());

        copyProperty(debitDB, "value", debit.getValue());

        copyProperty(debitDB, "dateCreated", debit.getDateCreated());

        copyProperty(debitDB, "year", debit.getYear());

        copyProperty(debitDB, "month", debit.getMonth());

        copyProperty(debitDB, "author", debit.getAuthor());

        copyProperty(debitDB, "category", debit.getCategory());
    }
    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value == null) return;
        super.copyProperty(dest, name, value);
    }
}

package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreditCardList implements DataAccess<CreditCard, String>, Serializable {
    private static CreditCardList creditCardList;
    public static List<CreditCard> creditCards = new ArrayList<CreditCard>();

    public static CreditCardList instance() {
        if (creditCardList == null) {
            return (creditCardList = new CreditCardList());
        } else {
            return creditCardList;
        }
    }

    @Override
    public void add(CreditCard item) {

    }

    @Override
    public List<CreditCard> getAll() {
        return null;
    }

    @Override
    public CreditCard remove(String id) {
        return null;
    }

    @Override
    public void removeAll() {

    }
}

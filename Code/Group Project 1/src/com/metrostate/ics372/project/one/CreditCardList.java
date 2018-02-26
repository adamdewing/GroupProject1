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

    public CreditCard find(String creditCardNumber){
        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
                return creditCards.get(i);
            }
        }
        return null;
    }

    public CreditCard remove(String creditCardNumber){
        CreditCard removedCard = null;

        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
                removedCard = creditCards.get(i);
                creditCards.remove(i);
            }
        }
        return removedCard;
    }

    @Override
    public void add(CreditCard newCard) {
        for(int i = 0; i < creditCards.size(); i++){
            if(!creditCards.get(i).getCreditCardNumber().equals(newCard.getCreditCardNumber())){
                creditCards.add(newCard);
                System.out.println("Successfully Added Card");
            }else{
                System.out.println("Failed to add card");
            }
        }
    }

    @Override
    public List<CreditCard> getAll() {
        return null;
    }

    @Override
    public void removeAll() {

    }
}

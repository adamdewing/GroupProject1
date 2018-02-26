package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
        String cardOwnerId = null;
        int ownerCardCount = 0;
        int targetCardIndex = 0;
        CreditCard targetCard = null;

        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
                targetCard = creditCards.get(i);
                cardOwnerId = creditCards.get(i).getCardOwnerId();
            }
        }

        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCardOwnerId().equals(cardOwnerId)){
                ownerCardCount++;
            }
        }

        if(targetCard != null && ownerCardCount > 1){
            creditCards.remove(targetCardIndex);
        }

        return targetCard;
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

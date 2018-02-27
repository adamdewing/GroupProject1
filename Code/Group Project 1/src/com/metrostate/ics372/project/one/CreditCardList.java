/**
 * Description: This class serves as the adaptor class which implements all of
 * the methods from the PushbackableTokenizer class. Additionally, the class
 * creates and maintains a reference to the adaptee being the Stack and
 * StringTokenizer objects.
 */

package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditCardList implements DataAccess<CreditCard, String>{
    private static CreditCardList creditCardList;
    public static List<CreditCard> creditCards = new ArrayList<CreditCard>();

    /**
     * Method which creates an instance of the CreditCardList Object.
     * @return CreditCardList
     */
    public static CreditCardList instance() {
        if (creditCardList == null) {
            return (creditCardList = new CreditCardList());
        } else {
            return creditCardList;
        }
    }

    /**
     * Method which searches through the available credit cards to find a match based
     * on the given credit card number.
     * @param creditCardNumber String value that represents a credit card number.
     * @return CreditCard
     */
    public CreditCard find(String creditCardNumber){
        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
                return creditCards.get(i);
            }
        }
        return null;
    }

    /**
     * Method
     * @param creditCardNumber
     * @return
     */
    public CreditCard remove(String creditCardNumber){
        String cardOwnerId = null;
        int ownerCardCount = 0;
        int targetCardIndex = -1;

        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
                targetCardIndex = i;
                cardOwnerId = creditCards.get(i).getCardOwnerId();
            }
        }

        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCardOwnerId().equals(cardOwnerId)){
                ownerCardCount++;
            }
        }

        if(targetCardIndex >=0 && ownerCardCount > 1){
            CreditCard tempCard = creditCards.get(targetCardIndex);
            creditCards.remove(targetCardIndex);
            return tempCard;
        }

        return null;
    }

    @Override
    public CreditCard add(CreditCard newCard) {
        boolean ownerExists = false;
        boolean cardExists = false;

        if(creditCards.size() == 0){
            creditCards.add(newCard);
            return newCard;
        }

        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCardOwnerId().equals(newCard.getCardOwnerId())){
                ownerExists = true;
            }
            if(creditCards.get(i).getCreditCardNumber().equals(newCard.getCreditCardNumber())){
                cardExists = true;
            }
        }

        if(ownerExists && !cardExists){
            creditCards.add(newCard);
            return newCard;
        }else{
            return null;
        }
    }

    @Override
    public List<CreditCard> getAll() {
        return creditCards;
    }

    @Override
    public void removeAll() {

    }

    public void printAll() {
        for(int i = 0; i < creditCards.size(); i++){
            System.out.println(creditCards.get(i).toString());
        }
    }
}

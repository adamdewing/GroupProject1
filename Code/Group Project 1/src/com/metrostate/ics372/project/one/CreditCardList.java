/**
 * Description: This class serves as a means to store Credit Card objects. This
 * class is used to retrieve any of the existing credit cards in the system.It
 * should be noted that this class implements the Data Access and Modified
 * interfaces.
 */

package com.metrostate.ics372.project.one;

import java.util.ArrayList;
import java.util.List;

public class CreditCardList implements DataAccess<CreditCard, String>, Modified{
    private static CreditCardList creditCardList;
    private static boolean isModified = false;
    private static List<CreditCard> creditCards = new ArrayList<CreditCard>();

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
     * Method which removes an existing credit card.
     * @param creditCardNumber String value that represents a credit card number.
     * @return CreditCard object.
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
            isModified = true;
            return tempCard;
        }

        return null;
    }

    /**
     * Method which adds an existing credit card. Forced implementation from
     * the DataAccess interface.
     * @param newCard Represents a CreditCard object.
     * @return CreditCard object.
     */
    @Override
    public CreditCard add(CreditCard newCard) {
        boolean ownerExists = false;
        boolean cardExists = false;

        if(CustomerList.instance().find(newCard.getCardOwnerId()) != null) {
        	ownerExists = true;
        }
        
        for(int i = 0; i < creditCards.size(); i++){
            if(creditCards.get(i).getCreditCardNumber().equals(newCard.getCreditCardNumber())){
                cardExists = true;
            }
        }

        if(ownerExists && !cardExists){
            creditCards.add(newCard);
            isModified = true;
            return newCard;
        }else{
            return null;
        }
    }

    /**
     * Method which retrieves and returns a List of type CreditCard holding all
     * existing CreditCard objects on file.
     * @return List<CreditCard>
     */
    @Override
    public List<CreditCard> getAll() {
        return creditCards;
    }

    /**
     * Method which removes all existing CreditCards within the creditCards List.
     */
    @Override
    public void removeAll() {
    	creditCards = new ArrayList<CreditCard>();
    	isModified = true;
    }

    /**
     * Displays all of the existing CreditCards within the creditCards List.
     */
    public void printAll() {
        for(int i = 0; i < creditCards.size(); i++){
            System.out.println(creditCards.get(i).toString());
        }
    }

	@Override
	public boolean isModified() {
		return isModified;
	}

	@Override
	public void resetModifiedFlag() {
		isModified = false;
	}
}

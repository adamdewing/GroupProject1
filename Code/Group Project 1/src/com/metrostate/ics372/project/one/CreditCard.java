/**
 * Description: This class serves as the means of identifying a real world
 * credit card and as such holds variables to identify a credit card and its owner.
 */

package com.metrostate.ics372.project.one;

import java.io.Serializable;

/**
 * Holds data related to credit card information.
 *
 */
public class CreditCard  implements Serializable{
    private String cardOwnerId;
    private String creditCardNumber;
    private String creditCardExpirationDate;
    private static final long serialVersionUID = 4916L;

    /**
     * Class constructor.
     * @param cardOwnerId String value that represents the customers unique identifier.
     * @param creditCardNumber String value that represents the customers credit
     *                         card number.
     * @param creditCardExpirationDate String value that represents the customers
     *                                 credit card expiration date.
     */
    public CreditCard(String cardOwnerId, String creditCardNumber,
                      String creditCardExpirationDate){
        this.cardOwnerId = cardOwnerId;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    //Getters and Setters for class variables.
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardOwnerId() {
        return cardOwnerId;
    }

    public String getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    //Modification of the standard toString method.
    public String toString(){
        return "Credit Card Number: " + this.creditCardNumber + "\n" +
                "Credit Card Expiration Date: " + this.creditCardExpirationDate + "\n";
    }
}

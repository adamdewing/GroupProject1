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

    public CreditCard(String cardOwnerId, String creditCardNumber, String creditCardExpirationDate){
        this.cardOwnerId = cardOwnerId;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardOwnerId() {
        return cardOwnerId;
    }

    public String getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    public String toString(){
        return "Credit Card Number: " + this.creditCardNumber + "\n" +
                "Credit Card Expiration Date: " + this.creditCardExpirationDate + "\n";
    }
}

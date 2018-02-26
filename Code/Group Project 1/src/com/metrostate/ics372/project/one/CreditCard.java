/**
 * Description: This class serves as the adaptor class which implements all of
 * the methods from the PushbackableTokenizer class. Additionally, the class
 * creates and maintains a reference to the adaptee being the Stack and
 * StringTokenizer objects.
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

package br.com.ernestobarbosa.springboottestrestassured.functions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tax {
    private final static Double simpleTax = 0.03;
    private final static Double federationTax = 0.13;
    private final static Double stateTax = 0.11;


    public static Double getSimpleTax(Double price){
        return price * simpleTax;
    }

    public static Double getFederationTax(Double price){
        return price * federationTax;
    }

    public static Double getStateTax(Double price){
        return price * stateTax;
    }

    public static Double getPriceAllTax(Double price){
        return price +
            getSimpleTax(price) +
            getFederationTax(price) +
            getStateTax(price);
    }

}

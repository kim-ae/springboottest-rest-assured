package br.com.ernestobarbosa.springboottestrestassured;

import static br.com.ernestobarbosa.springboottestrestassured.functions.Tax.getFederationTax;
import static br.com.ernestobarbosa.springboottestrestassured.functions.Tax.getPriceAllTax;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.ernestobarbosa.springboottestrestassured.functions.Tax;

public class SimpleTests {
    private final static Double FEDERATION_TAX = 0.13;

    @Test
    public void simpleTaxTest(){
        assertTrue(Tax.getSimpleTax(1.0).equals(0.03));
    }

    @Test
    public void federationTaxTest(){
        assertTrue(getFederationTax(5.32).equals(FEDERATION_TAX*5.32));
    }

    @Test
    public void priceAllTaxTest(){
        assertTrue(getPriceAllTax(16.73).equals(21.2471));
    }
}

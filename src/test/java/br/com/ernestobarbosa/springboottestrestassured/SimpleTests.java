package br.com.ernestobarbosa.springboottestrestassured;

import br.com.ernestobarbosa.springboottestrestassured.functions.Tax;
import org.junit.Test;

import static br.com.ernestobarbosa.springboottestrestassured.functions.Tax.getFederationTax;
import static br.com.ernestobarbosa.springboottestrestassured.functions.Tax.getPriceAllTax;
import static org.junit.Assert.assertTrue;

public class SimpleTests {

    @Test
    public void simpleTaxTest(){
        assertTrue(Tax.getSimpleTax(1.0).equals(0.03));
    }

    @Test
    public void federationTaxTest(){
        assertTrue(getFederationTax(5.32).equals(0.6916));
    }

    @Test
    public void priceAllTaxTest(){
        assertTrue(getPriceAllTax(16.73).equals(21.2471));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author Zeljana
 */
public class PromotionServiceTest {
    private static PromotionService service;
    public PromotionServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        service = new PromotionService();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateSeasonalPromotions method, of class PromotionService.
     */
    @Test
    public void testCheckOrderHoliday() {
        
        PromotionService promoSpy = spy(service);
        Date d = new Date();
        promoSpy.applyPromotions(200.0f, d);
        when(promoSpy.checkHolidays(d)).thenReturn(true);
        InOrder applyPromoOrder = inOrder(promoSpy);
        
        applyPromoOrder.verify(promoSpy).calculateSpecialPromotions();
        applyPromoOrder.verify(promoSpy).checkHolidays(d);
        applyPromoOrder.verify(promoSpy).calculateSeasonalPromotions(d);
        applyPromoOrder.verify(promoSpy).calculateDiscount();

    }
    
    @Test
    public void testCheckOrderNotHoliday() {
        
        PromotionService promoSpy = spy(service);
        Date d = new Date();
        promoSpy.applyPromotions(200.0f, d);
        when(promoSpy.checkHolidays(d)).thenReturn(false);
        InOrder applyPromoOrder = inOrder(promoSpy);
        
        applyPromoOrder.verify(promoSpy).calculateSpecialPromotions();
        applyPromoOrder.verify(promoSpy).checkHolidays(d);
        applyPromoOrder.verify(promoSpy).calculateDiscount();

    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.AnimationBase;
import goldteam.domain.Attackable;
import goldteam.domain.HudAnimationBase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gordon
 */
public class HeartHudItemTest {
    
    public HeartHudItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of Update method, of class HeartHudItem.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update");
        HeartHudItem instance = null;
        instance.Update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWatcher method, of class HeartHudItem.
     */
    @Test
    public void testGetWatcher() {
        System.out.println("getWatcher");
        HeartHudItem instance = null;
        Attackable expResult = null;
        Attackable result = instance.getWatcher();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWatcher method, of class HeartHudItem.
     */
    @Test
    public void testSetWatcher() {
        System.out.println("setWatcher");
        Attackable target = null;
        HeartHudItem instance = null;
        instance.setWatcher(target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GraphicsUpdateHandler method, of class HeartHudItem.
     */
    @Test
    public void testGraphicsUpdateHandler() {
        System.out.println("GraphicsUpdateHandler");
        HeartHudItem instance = null;
        instance.GraphicsUpdateHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UpdateEffectHandler method, of class HeartHudItem.
     */
    @Test
    public void testUpdateEffectHandler() {
        System.out.println("UpdateEffectHandler");
        HeartHudItem instance = null;
        instance.UpdateEffectHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MapUpdateTimerHandler method, of class HeartHudItem.
     */
    @Test
    public void testMapUpdateTimerHandler() {
        System.out.println("MapUpdateTimerHandler");
        HeartHudItem instance = null;
        instance.MapUpdateTimerHandler();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnimator method, of class HeartHudItem.
     */
    @Test
    public void testGetAnimator() {
        System.out.println("getAnimator");
        HeartHudItem instance = null;
        AnimationBase expResult = null;
        AnimationBase result = instance.getAnimator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

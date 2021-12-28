package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

    Base base;
    Missile mis;
    RocketLauncher rl;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();
        

        mis = new Missile();
        rl = new RocketLauncher();
    }

    @Test
    void testWeapon() {
        assertEquals(3, mis.getShotLeft());

        // TODO: add more cases
        assertEquals(20,rl.getShotLeft());
        
        mis.decrementShotLeft();
        assertEquals(2,mis.getShotLeft());
        
        rl.decrementShotLeft();
        assertEquals(19,rl.getShotLeft());

    }

    @Test
    void testGetWeaponType() {
        assertEquals("missile", mis.getWeaponType().toLowerCase());

        // TODO: add more cases
        assertEquals("rocketlauncher",rl.getWeaponType().toLowerCase());
        
        Missile mis2 = new Missile();
        RocketLauncher rl2 = new RocketLauncher();
        assertEquals("missile",mis2.getWeaponType().toLowerCase());
        assertEquals("rocketlauncher",rl2.getWeaponType().toLowerCase());
       
        
    }

    
    @Test
    void testGetShotLeft() {
        mis.shootAt(0, 0, this.base);
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases
        rl.shootAt(0, 0, base);
        assertEquals(19,rl.getShotLeft());
        
        mis.shootAt(1,3,this.base);
        assertEquals(1,mis.getShotLeft());
        
        rl.shootAt(2,5,base);
        assertEquals(18,rl.getShotLeft());
        
    }

    @Test
    void testDecrementShotleft() {
        mis.decrementShotLeft();
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases
        mis.decrementShotLeft();
        assertEquals(1,mis.getShotLeft());
        
        rl.decrementShotLeft();
        assertEquals(19,rl.getShotLeft());
        
        rl.decrementShotLeft();
        assertEquals(18,rl.getShotLeft());
    }

    @Test
    void testShootAt() {
        mis.shootAt(0, 0, this.base);
        assertTrue(base.getTargetsArray()[0][0].isHitAt(0, 0));
        assertEquals(1, base.getShotsCount());
        // TODO: add more cases
        mis.shootAt(1, 3, base);
        assertTrue(base.getTargetsArray()[1][3].isHitAt(1,3));
        
        rl.shootAt(2, 4, base);
        assertTrue(base.getTargetsArray()[2][4].isHitAt(2, 4));
        
        rl.shootAt(6, 5, base);
        assertTrue(base.getTargetsArray()[6][5].isHitAt(6,5));
    }

}

package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TargetTest {

    Base base;
    Armory armory;
    Barrack barrack;
    SentryTower st;
    Tank tank;
    OilDrum od;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();

        armory = new Armory(base);
        base.placeTargetAt(armory, 0, 0, true);

        barrack = new Barrack(base);
        base.placeTargetAt(barrack, 0, 4, true);

        st = new SentryTower(base);
        base.placeTargetAt(st, 2, 4, true);

        tank = new Tank(base);
        base.placeTargetAt(tank, 1, 3, true);

        od = new OilDrum(base);
        base.placeTargetAt(od, 2, 1, true);
    }

    @Test
    void testTarget() {

        // Armory
        assertEquals(2, armory.getHit().length);
        assertEquals(3, armory.getHit()[0].length);

        // Barrack
        assertEquals(1, barrack.getHit().length);
        assertEquals(3, barrack.getHit()[0].length);


        // TODO: add more cases
        //SentryTower
        assertEquals(1,st.getHit().length);
        assertEquals(1,st.getHit()[0].length);
        //Tank
        assertEquals(1,tank.getHit().length);
        assertEquals(1,tank.getHit()[0].length);
        //OilDrum
        assertEquals(1,od.getHit().length);
        assertEquals(1,od.getHit()[0].length);
    }

    @Test
    void testToString() {
        assertEquals("O", st.toString());
        assertEquals("T", tank.toString());

        // TODO: add more cases
        assertEquals("O",armory.toString());
        assertEquals("O",barrack.toString());
        assertEquals("O",od.toString());
    }

    @Test
    void testGetTargetName() {
        assertEquals("tank", tank.getTargetName().toLowerCase());
        assertEquals("sentrytower", st.getTargetName().toLowerCase());
        assertEquals("oildrum", od.getTargetName().toLowerCase());

        // TODO: add more cases
        assertEquals("armory", armory.getTargetName().toLowerCase());
        assertEquals("barrack", barrack.getTargetName().toLowerCase());
        Target am = new Armory(this.base);
        assertEquals("armory",am.getTargetName().toLowerCase());
    }

    @Test
    void testExplode() {
        assertFalse(armory.isDestroyed());
        od.explode();
        assertTrue(armory.isDestroyed());

        // TODO: add more cases
        assertTrue(tank.isDestroyed());
        assertTrue(st.isDestroyed());
        assertFalse(barrack.isDestroyed());
        
        Target tk = new Tank(this.base);
        Target st1 = new SentryTower(this.base);
        this.base.placeTargetAt(tk, 9, 9, true);
        this.base.placeTargetAt(st1, 8,8, true);
        tk.explode();
        assertTrue(st1.isDestroyed());
        
        Target od2 = new OilDrum(this.base);
        Target st2 = new SentryTower(this.base);
        this.base.placeTargetAt(od2, 9, 9, true);
        this.base.placeTargetAt(st2, 8, 8, true);
        od2.explode();
        assertTrue(st2.isDestroyed());
    }


    @Test
    void testGetShot() {
        Target am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, false);
        am.getShot(5, 6);
        assertEquals(1, am.getHit()[0][1]);
        
        
        // TODO: add more cases
        armory.getShot(0, 0);
        assertEquals(1,armory.getHit()[0][0]);
        
        armory.getShot(0, 1);
        assertEquals(1,armory.getHit()[0][1]);
        assertEquals(0,armory.getHit()[0][2]);
        assertEquals(0,barrack.getHit()[0][0]);
        
        barrack.getShot(0, 6);
        assertEquals(1,barrack.getHit()[0][2]);
        
    }

    @Test
    void testIsDestroyed() {
        assertFalse(od.isDestroyed());
        od.getShot(2, 1);
        assertTrue(od.isDestroyed());
        assertTrue(tank.isDestroyed());

        // TODO: add more cases
        barrack.getShot(0, 6);
        assertTrue(barrack.isDestroyed());
        assertTrue(st.isDestroyed());
        
        Target tank = new Tank(this.base);
        this.base.placeTargetAt(tank, 9, 9, true);
        assertFalse(tank.isDestroyed());
        tank.getShot(9, 9);
        assertFalse(tank.isDestroyed());
        tank.getShot(9, 9);
        assertTrue(tank.isDestroyed());
        
        Target st =  new SentryTower(this.base);
        this.base.placeTargetAt(st, 8, 8, true);
        assertFalse(st.isDestroyed());
        st.getShot(8, 8);
        assertTrue(st.isDestroyed());
        

    }

    @Test
    void testIsHitAt() {
        Target am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, true);
        assertFalse(am.isHitAt(5, 5));
        am.getShot(5, 5);
        assertTrue(am.isHitAt(5, 5));

        // TODO: add more cases
        Target od3 = new OilDrum(this.base);
        assertTrue(this.base.okToPlaceTargetAt(od3, 7, 7, false));
        this.base.placeTargetAt(od3, 7, 7, true);
        assertFalse(od3.isHitAt(7, 7));
        od3.getShot(7, 7);
        assertTrue(od3.isHitAt(7, 7));
        
        
        Target tank = new Tank(this.base);
        assertTrue(this.base.okToPlaceTargetAt(tank, 9, 9, false));
        this.base.placeTargetAt(tank, 9, 9, true);
        assertFalse(tank.isHitAt(9, 9));
        tank.getShot(9, 9);
        assertTrue(tank.isHitAt(9, 9));
        
        Target st3 = new SentryTower(this.base);
        assertTrue(this.base.okToPlaceTargetAt(st3, 5, 9, false));
        this.base.placeTargetAt(st3, 8, 8, true);
        assertFalse(st3.isHitAt(8, 8));
        st3.getShot(8,8);
        assertTrue(st3.isHitAt(8,8));

    }

}

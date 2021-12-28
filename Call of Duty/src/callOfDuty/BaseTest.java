package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseTest {

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
    void testBase() {
        assertEquals(10, base.getTargetsArray().length);

        // TODO: add more cases
        assertEquals(10,base.getTargetsArray()[0].length);
        assertEquals(0,base.getShotsCount());
        assertEquals(0,base.getDestroyedTargetCount());
    }

    @Test
    void testPlaceAllTargetRandomly() {
        this.base = new Base();
        base.placeAllTargetRandomly();
        List<Target> list = new ArrayList<Target>();
        int headQuarterCount = 0;
        int armoryCount = 0;
        int barracksCount = 0;
        int sentryCount = 0;
        int tanksCount = 0;
        int odCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (base.getTargetsArray()[i][j].getTargetName() != "ground") {
                    if (!list.contains(base.getTargetsArray()[i][j])) {
                        list.add(base.getTargetsArray()[i][j]);
                        switch (base.getTargetsArray()[i][j].getTargetName().toLowerCase()) {
                        case "armory": {
                            armoryCount++;
                            break;
                        }
                        case "headquarter": {
                            headQuarterCount++;
                            break;
                        }
                        case "barrack": {
                            barracksCount++;
                            break;
                        }
                        case "sentrytower": {
                            sentryCount++;
                            break;
                        }
                        case "tank": {
                            tanksCount++;
                            break;
                        }
                        case "oildrum": {
                            odCount++;
                            break;
                        }
                        }
                    }
                }
            }
        }
        assertEquals(list.size(), 18);

        assertEquals(1, headQuarterCount);
        assertEquals(2, armoryCount);
        assertEquals(3, barracksCount);
        assertEquals(4, sentryCount);
        assertEquals(4, tanksCount);
        assertEquals(4, odCount);
    }

    @Test
    void testOkToPlaceTargetAt() {
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 7, false));
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, true));
        assertTrue(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, false));

        // TODO: add more cases
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 1, 8, true));
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 1, 9, true));
        assertTrue(this.base.okToPlaceTargetAt(new Barrack(this.base), 9, 6, true));
        
        assertFalse(this.base.okToPlaceTargetAt(new Tank(this.base), 0, 0, false));
        assertTrue(this.base.okToPlaceTargetAt(new Tank(this.base), 9, 9, false));
        
        assertFalse(this.base.okToPlaceTargetAt(new SentryTower(this.base), 0, 0, false));
        assertTrue(this.base.okToPlaceTargetAt(new SentryTower(this.base), 9, 9, false));
        
    }
    
    

    @Test
    void testPlaceTargetAt() {
        Target armory = new Armory(base);
        this.base.placeTargetAt(armory, 5, 5, false);
        assertEquals(5, armory.getCoordinate()[0]);
        assertEquals(5, armory.getCoordinate()[1]);
        assertEquals(3, armory.getHit().length);
        assertEquals(2, armory.getHit()[0].length);
        
     // TODO: add more cases
        Target tank = new Tank(base);
        this.base.placeTargetAt(tank, 9, 9, false);
        assertEquals(9, tank.getCoordinate()[0]);
        assertEquals(9, tank.getCoordinate()[1]);
        assertEquals(1, tank.getHit().length);
        assertEquals(1, tank.getHit()[0].length);
        
        Target st1 = new SentryTower(base);
        this.base.placeTargetAt(st1, 8,8, false);
        assertEquals(8, st1.getCoordinate()[0]);
        assertEquals(8, st1.getCoordinate()[1]);
        assertEquals(1, st1.getHit().length);
        assertEquals(1, st1.getHit()[0].length);
        
        Target od1 = new OilDrum(base);
        this.base.placeTargetAt(od1, 9, 6, false);
        assertEquals(9, od1.getCoordinate()[0]);
        assertEquals(6, od1.getCoordinate()[1]);
        assertEquals(1, od1.getHit().length);
        assertEquals(1, od1.getHit()[0].length);
    }
    
    
    @Test
    void testIsOccupied() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 0, 0, true);
        assertTrue(base.isOccupied(0, 0));

        // TODO: add more cases
        Tank tank = new Tank(base);
        this.base.placeTargetAt(tank, 9, 9, true);
        assertTrue(base.isOccupied(9,9));
        
        SentryTower st = new SentryTower(base);
        this.base.placeTargetAt(st,5,5,true);
        assertTrue(base.isOccupied(5,5));
        
        OilDrum od = new OilDrum(base);
        this.base.placeTargetAt(od, 8, 8, true);
        assertTrue(base.isOccupied(8,8));
        
    }

    @Test
    void testShootAt() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 5, 5, true);

        base.shootAt(5, 5);
        assertTrue(arm.isHitAt(5, 5));

        // TODO: add more cases
        Tank tank = new Tank(base);
        this.base.placeTargetAt(tank, 9, 9, true);
        
        base.shootAt(9, 9);
        assertTrue(tank.isHitAt(9, 9));
        
        SentryTower st = new SentryTower(base);
        this.base.placeTargetAt(st,0,0,true);
        
        base.shootAt(0, 0);
        assertTrue(st.isHitAt(0, 0));
        
        OilDrum od = new OilDrum(base);
        this.base.placeTargetAt(od, 8, 8, true);
        
        base.shootAt(8, 8);
        assertTrue(od.isHitAt(8, 8));
        
        
        
    }

    @Test
    void testIsGameOver() {

        assertFalse(base.isGameOver(new RocketLauncher(), new Missile()));

        // TODO: add more cases
        RocketLauncher rl=new RocketLauncher();
        Missile ml=new Missile();
        for(int i=0;i<20;i++) {
        	rl.decrementShotLeft();
        }
        for(int i=0;i<3;i++) {
        	ml.decrementShotLeft();
        }
        assertTrue(base.isGameOver(rl, ml));
        
        RocketLauncher rl1=new RocketLauncher();
        Missile ml1=new Missile();
        for(int i=0;i<18;i++) {
        	rl.decrementShotLeft();
        }
        for(int i=0;i<1;i++) {
        	ml.decrementShotLeft();
        }
        assertFalse(base.isGameOver(rl1, ml1));
        
        RocketLauncher rl2=new RocketLauncher();
        Missile ml2=new Missile();
        this.base.setDestroyedTargetCount(18);
        assertTrue(base.isGameOver(rl2,ml2));
        
    }

    @Test
    void testWin() {
        assertFalse(this.base.win());

        // TODO: add more cases
        this.base.setDestroyedTargetCount(18);
        assertTrue(this.base.win());
        
        this.base.setDestroyedTargetCount(5);
        assertFalse(this.base.win());
        
        this.base.setDestroyedTargetCount(0);
        assertFalse(this.base.win());
        
    }

    @Test
    void testIncrementAndSetShotsCount() {

        assertEquals(0, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(1, base.getShotsCount());

        // TODO: add more cases
        base.incrementShotsCount();
        assertEquals(2, base.getShotsCount());
        
        base.incrementShotsCount();
        assertEquals(3, base.getShotsCount());
        
        base.incrementShotsCount();
        assertEquals(4, base.getShotsCount());
    }

    @Test
    void testSetAndGetDestroyedTargetCount() {
        base.setDestroyedTargetCount(10);
        assertEquals(10, base.getDestroyedTargetCount());

    }

  

    @Test
    void testGetTargetsArray() {
        assertEquals(10, base.getTargetsArray().length);

    }


}

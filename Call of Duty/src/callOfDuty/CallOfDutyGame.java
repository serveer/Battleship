package callOfDuty;
import java.util.Scanner;
/**
 * This is the “main” class, containing the main method, which starts by 
 * creating an instance of Base. 
 * @author Jonathan Shaw, Zihu Xu
 *
 */
public class CallOfDutyGame {

	public static void main(String[] args) {
		//scanner object for user input
		Scanner sc = new Scanner(System.in);
		//initialize base and weapon
		Base base=new Base();
		Missile missile=new Missile();
		RocketLauncher rl=new RocketLauncher();
		Weapon currentWeapon=rl;
		//place objects on base
		base.placeAllTargetRandomly();
		//while game is not over
		while(!base.isGameOver(missile,rl)) {
			//keep printing array and weapon info
			base.print();
			System.out.println();
			System.out.println("RPG: "+rl.getShotLeft()+" Missle: "+missile.getShotLeft());
			System.out.println("Your current weapon is: "+currentWeapon.getWeaponType());
			//get user input
			System.out.print("Enter row,column, or q to switch a weapon ");
			String response=sc.nextLine();
			//if user input can be converted to coordinate, shoot at the coordinate
			try {
				//split user input to two strings
				int[]coordinate=new int[2];
				String []strCoord=new String[2];
				strCoord=response.split(",");
				//try to convert to int
				for(int i = 0;i < strCoord.length;i++) {
					coordinate[i]=Integer.parseInt(strCoord[i]);
				}
				//if weapon shot is larger than 1
				if (currentWeapon.getShotLeft()>0) {
					//shoot at coordinate
					currentWeapon.shootAt(coordinate[0], coordinate[1], base);
				}else {
					//else say no ammunition to user
					System.out.println("No ammunition!!");
				}
			}catch(Exception e) {
				//if user input fails to turn to int, change weapon
				if (response.equals("q")&&currentWeapon.equals(rl)) {
					//change to missile when current is RPG
					currentWeapon=missile;
				}else if(response.equals("q")&&currentWeapon.equals(missile)) {
					//change to RPG whe current is missile
					currentWeapon=rl;
				}else {
					//if not q output wrong input
					System.out.println("Wrong input");
				}
			}
		}
		
	}

}

/*
Author: Prithvi Patel
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Tester
{

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("SampleInput"));
		int row  = sc.nextInt();
		int column = sc.nextInt();
		int bomb = sc.nextInt();
		char[][] arr = new char[row + 2][column + 2];
		for(int i = 0; i < row + 2; i++)
		{
		for(int j = 0; j < column + 2; j++)	   // empty cells
			{
				arr[i][j] = '-';
			}
		}
		int bombs = 0;
		for(int k = 0; k < bomb; k++)      
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			for(int i = 1; i < row + 1; i++)      // locating bomb positions
			{
				for(int j = 1; j < column + 1; j++)
				{
					if((i - 1) == x && (j - 1) == y)         //because array with one extra row at below, other extra row at below, extra column at first
					{						//and another extra column at last is taken
						arr[i][j] = '*';		
						bombs++;
					}
				}
			}
		}
		int lives = sc.nextInt();		
		Game G = new Game(lives,row,column,arr,bombs);	
		sc.close();
		sc = new Scanner(System.in); // start taking the user input
		
		
		boolean[] b =new boolean[2];   //for the continuation or discontinuation of input
		b[0] =true;                    // in the following while loop
		b[1] = true;
		
		
		while(b[0])					  // b[0] correspond to check method
		{
			System.out.print("INPUT ");
			String i = sc.nextLine();
			b = G.check(i,null);
			
			
			if(b[0]&&!b[1])		 //b[1] corresponds to checkForWinner method 
			{					 //b[1] is false means winner is found
					b[0] = false;					
					break;
			}
			 
			 else if(!b[0]&&b[1])     //b[0] is false when bomb is checked
			{				
				System.out.print("INPUT ");
				String s = sc.nextLine();
				
				if(s.equals("undo")&&(G.Undoleft)>=0)      //giving user the chance to undo
				{
					
					b = G.check(s,i);
					if(!b[0])   // undoes are not left and previous input i had checked a bomb
						System.out.println("Sorry, you loose the game as no more undoes are available");
				}
				else
				{
					System.out.println("Sorry, you loose the game");
					break;
				}
			}
			
		}
		
		//G.display(G.actualGrid);  // displays actual grid after game is over
		sc.close();
		

	}
	

}

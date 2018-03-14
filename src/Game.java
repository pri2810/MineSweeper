/*
Author: Prithvi Patel
*/
public class Game
{
	char[][] actualGrid ;
	int Rows;
	int Columns;
	int Undoleft;
	int undo;
	int Bombs;
	char[][] dummyGrid;     //stores present moves according to input
	int Moves = 0;
	char[][][] previous;    // storing previous moves arrays
	int m = 0;

	
	
	public Game(int l,int r,int c,char[][] arr,int b)   //making actual grid and creating dummy 
	{										//for user inside game constructor
		this.Undoleft = l;								// new Game is new Object
		this.undo = 0;
		this.Rows = r;
		this.Columns = c;
		actualGrid = new char[Rows][Columns];
		dummyGrid = new char[Rows][Columns];
		previous = new char[100][Rows][Columns];
		for(int i=1;i<r+1;i++)
		{
			for(int j=1;j<c+1;j++)
			{
				int count = 0;
				if(arr[i][j]!='*')				//calculating number of bombs around 
				{								//each non'*' (bomb) cell
					for(int k=i-1;k<i+2;k++)				
						for(int m=j-1;m<j+2;m++)						
							if(arr[k][m]=='*')
								count++;		// number of bombs surrouned by arr[i][j]
					arr[i][j] = (char)(count+48);					
				}
			}
		}
		Bombs = b;
		for(int i=1;i<r+1;i++)              // making actualGrid[m][n] from arr[m+2][n+2] and 
			{								//initialising dummyGrid with all cells of it 
			for(int j=1;j<c+1;j++)			//as '-'
				{
				actualGrid[i-1][j-1] = arr[i][j];
				dummyGrid[i-1][j-1] = '-';
				}
			}
		//display(actualGrid);   // to display actual grid
	}
	
	public boolean[] check(String input,String previous_input)
	{
		int q  = m;
	  
		for(int i=0;i<Rows;i++)				 //assigning dummyGrid to store it as previous
			for(int j=0;j<Columns;j++)		 //before changing it for input(new)
				previous[q][i][j] = dummyGrid[i][j] ;
		Moves++;
		m++;						
		boolean[] b = new boolean[2];
		b[0] = true;
		b[1] = true;
		
		
		if(input.equals("undo"))     //input by user is undo
		{
			Undoleft--;
			undo++;
			int x,y,f;
			if(previous_input!=null)    // when previous move was of checking bomb
			{
				String[] s = previous_input.split(" ");
				x = Integer.parseInt(s[0]);
				y = Integer.parseInt(s[1]);
				f = Integer.parseInt(s[2]);
				if((Undoleft+1)==0&&actualGrid[x][y]=='*'&&f==1)
				{
					b[0]= false;      // undoes are over so user looses the game 
					return b;		  //thus b[0] is false to break loop
				}			
			}
			if(m==1)				// user gives more undo than Moves(excluding the 
			{						//already undone ones)
				m=0;
				Undoleft++;
				System.out.println("can't undo more");
				undo--;
			}
			if(Undoleft==-1)
			{
				Undoleft = 0;
				undo--;
				m = m-1;
				System.out.println("no more undoes left");
			}
			else 
				m = m-2;
			if(m<0)
				m = 0;
			for(int i=0;i<Rows;i++)
				for(int j=0;j<Columns;j++)	
						dummyGrid[i][j] = previous[m][i][j];		
		}
		
		else               //input is x y flag/check
		{
			String[] s = input.split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			int f = Integer.parseInt(s[2]);
			for(int i=0;i<Rows;i++)
				for(int j=0;j<Columns;j++)		
					if(i==x&&y==j)
					{
						if(f==1&&(dummyGrid[i][j]=='-'||dummyGrid[i][j]=='|'))  
							{		//checking is possible for already flagged ones
								dummyGrid[i][j] = actualGrid[i][j];
								boolean b2 = this.checkForWinner(dummyGrid);
								
								if(actualGrid[i][j]=='*')  //bomb is encountered
								{	
									b[0] = false;		// return for one more input
									return b;
								}
								b[1] = b2;
								return b;
								
							}
						else if(f==0&&dummyGrid[i][j]=='-')
							{   //flagging is only possible for non checked
								dummyGrid[i][j] = '|';
								if(actualGrid[i][j]=='*')
										Bombs--;
							
							
							}
						else
							{			//invalid input or alraedy checked is tried to be flagged
								System.out.println("invalid");
								return b;
							}
					}				
		}		
				
		boolean b2 = this.checkForWinner(dummyGrid);
		b[1] = b2;		
		return b;

	}
	
	
	public void display(char[][] arr)   //displaying moves and undo left
	{
		if(Undoleft>=0)
		System.out.println("Undoes left "+Undoleft);
		for(int i=0;i<Rows;i++)
		{
			for(int j=0;j<Columns;j++)
				System.out.print(arr[i][j]);
			System.out.println();
		}
	}
	
	
	public boolean checkForWinner(char[][] arr)    //checking winning condition
	{
		boolean b = true;
		int count = 0;
		
		
		for(int i=0;i<Rows;i++)
		{
			for(int j=0;j<Columns;j++)
			{
				if((arr[i][j]=='|'&&actualGrid[i][j]=='*'&&Bombs==0)||(arr[i][j]==actualGrid[i][j]))
					{
					count++;
					}
			}
		}
		
		if(count==Rows*Columns)  // all numbers are checked and bombs are flagged
		{		
			System.out.println("You won the game with "+Moves+" moves and "+undo+" undoes.");
			b = false;	
		}
		
		
		display(dummyGrid);
		


		return b;
	}
	
}

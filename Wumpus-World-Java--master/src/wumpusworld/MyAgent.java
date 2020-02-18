package wumpusworld;

/**
 * Contains starting code for creating your own  World agent. Currently
 * the agent only make a random decision each turn. We 
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent {

	private World w;
	int rnd;
	int  k = 1, fp = 0;
	int  path = 0;
	int[][] room = new int[4][4];
	int[] b = new int[40];
	
	int min=2000;

	/**
	 * Creates a new instance of your solver agent.
	 * 
	 * @param world Current world state
	 */
	public MyAgent(World world) {
		w = world;
	}

//-----------------------------------------------------------------

	// Direct Method which takes integer as input
	// [ possible values :1-Right,2-Left,3-Up,4-down]
	// 12+4 possibilities
	public void direction(int x) {
		int i = 0, dif;

		if (w.getDirection() == World.DIR_RIGHT) {
			
			i = 1;
		}
		if (w.getDirection() == World.DIR_LEFT) {
			
			i = 2;
		}
		if (w.getDirection() == World.DIR_UP) {
			
			i = 3;
		}
		if (w.getDirection() == World.DIR_DOWN) {
			
			i = 4;
		}
		dif = i - x;

		if (dif == 0)
			w.doAction(World.A_MOVE);
		if ((i == 1 && x == 2) || (x == 1 && i == 2) || (i == 3 && x == 4) || (x == 3 && i == 4)) {
			w.doAction(World.A_TURN_RIGHT);
			w.doAction(World.A_TURN_RIGHT);
			w.doAction(World.A_MOVE);

		}

		if ((i == 4 && x == 2) || (i == 2 && x == 3) || (i == 3 && x == 1) || (i == 1 && x == 4)) {
			w.doAction(World.A_TURN_RIGHT);
			w.doAction(World.A_MOVE);
		}

		if ((x == 4 && i == 2) || (x == 2 && i == 3) || (x == 3 && i == 1) || (x == 1 && i == 4)) {
			w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_MOVE);
		}

	}

// -----------------------------------------------------------------

	// Shoot arrow method which takes direction as input shoots the 
	// [ possible values :1-Right,2-Left,3-Up,4-down]
	// 12+4 possibilities
	public void arrow(int x) {
		int i = 0, dif;

		if (w.getDirection() == World.DIR_RIGHT) {
			
			i = 1;
		}
		if (w.getDirection() == World.DIR_LEFT) {
			
			i = 2;
		}
		if (w.getDirection() == World.DIR_UP) {
			
			i = 3;
		}
		if (w.getDirection() == World.DIR_DOWN) {
			
			i = 4;
		}
		dif = i - x;
		if (dif == 0)
			w.doAction(World.A_SHOOT);
		if ((i == 1 && x == 2) || (x == 1 && i == 2) || (i == 3 && x == 4) || (x == 3 && i == 4)) {
			w.doAction(World.A_TURN_RIGHT);
			w.doAction(World.A_TURN_RIGHT);
			w.doAction(World.A_SHOOT);

		}

		if ((i == 4 && x == 2) || (i == 2 && x == 3) || (i == 3 && x == 1) || (i == 1 && x == 4)) {
			w.doAction(World.A_TURN_RIGHT);
			w.doAction(World.A_SHOOT);
		}

		if ((x == 4 && i == 2) || (x == 2 && i == 3) || (x == 3 && i == 1) || (x == 1 && i == 4)) {
			w.doAction(World.A_TURN_LEFT);
			w.doAction(World.A_SHOOT);
		}

	}

//-------------------------------------------------------------------------------------------	

	// it will provide a shortest path between the current node and destination node
	// this method is used when the game is left with very less unvisited nodes.
	// and also it is safe path
	// it will fall on pit if there is no path exists
	
//-------------------------------------------------------------------------------------------	

	// if all the surrounded unvisited nodes contain breezes it gives the optimal
	// path with less probability

	
//-------------------------------------------------------------------------------------------	

	// it will find the best path to unvisited safe node.
	public void path(int[][] room, int[] a, int x, int y, int c1, int move,int score) {

		if (x <= 3 && y <= 3 && x >= 0 && y >= 0) {
		
			if (!w.isVisited(y + 1, 4 - x) ) {
               score=score+room[x][y];
             
				if(min>score) {
				min=score;
				if((c1-1)>0) 
				{
				path=1;
				k = 1;
				fp = c1-1;
				
				}
				if((c1-1)==0)
				{
					path=0;
					fp=c1-1;
				}
				
				
				for (int i = 0; i < c1; i++)
					b[i] = a[i];
				
				}
				}
				
			else

			 {
				if(c1<20)
					if (x <= 3 && y <= 3 && x >= 0 && y >= 0 )
						 {
						 
						 score=score+room[x][y];
							if (move != 1 && (y + 1) <= 3 ) {

								// movie right
								 
									if(c1<20) {
									a[c1] = 1;
								
									path(room, a, x, y + 1, c1+1, 2,score);
									
									a[c1] = 0;
									}
							}
							if (move != 2 && (y - 1) >= 0  ) {
								

									if(c1<20)
									{
									a[c1] = 2;
									
									path(room, a, x, y - 1, c1+1, 1,score);
								
									a[c1] = 0;
									}
									
								
							}
							if (move != 3 && x - 1 >= 0 ) {
								

									if(c1<20)
									{
									a[c1] = 3;
									
									path(room, a, x - 1, y, c1+1, 4,score);
					
									a[c1] = 0;
									}
									
							}
							if (move != 4 && x + 1 <= 3  ) {
				

									if(c1<20)
									{
									a[c1] = 4;
									
									path(room, a, x + 1, y, c1+1, 3,score);
									
									a[c1] = 0;
									}
									
								
							}
						}

			}

	}
	}

//-------------------------------------------------------------------------------------------	

	// if the box contains both the breeze and stench the methid will be executed
	// will see the probabilities of adjacent blocks and move accordingly
	// and update the probabilities to the adjacent boxes.
	public void breezeAndStrenchMethod(int[][] room, int cX, int cY, int x, int y) {
		int count = 0, Y = 0, X = 0, t1 = 0, d = 0, count1 = 0,d1=0;

		int[] a = new int[40];
		if(room[x][y]!=605)
		room[x][y] = 1;
		path = 0;

		if (x - 1 >= 0) {
			if (!w.isVisited(cX, cY + 1)) {
				if (room[x - 1][y] != 1 && room[x - 1][y] != 605) {
					if (!(room[x - 1][y] % 20 == 0 && room[x - 1][y] > 0)) {
						if (room[x - 1][y] == 125 || room[x - 1][y] == 165 || room[x - 1][y] == 1005 ) {

							count1++;
							X = x - 1;
							Y = y;

							d = 3;

						} else {
							X = x - 1;
							Y = y;
							room[x - 1][y] =165 ;
							d1 = 3;
							count++;
						}
					}
				}
			}
		}

		if (y + 1 <= 3) {
			if (!w.isVisited(cX + 1, cY)) {
				if (room[x][y + 1] != 1  && room[x][y + 1] != 500) {
					if (!(room[x][y + 1] % 20 == 0 && room[x][y + 1] > 0)) {
						if (room[x][y + 1] == 125 || room[x][y + 1] ==165  || room[x][y + 1] == 1005) {

							count1++;
							X = x;
							Y = y + 1;

							d = 1;

						} else {
							X = x;
							Y = y + 1;
							d1 = 1;
							room[x][y + 1] = 165;
							count++;
						}
					}
				}
			}

		}
		if (x + 1 <= 3) {
			if (!w.isVisited(cX, cY - 1)) {
				if (room[x + 1][y] != 1 && room[x + 1][y] != 500) {
					if (!(room[x + 1][y] % 20 == 0 && room[x + 1][y] > 0)) {
						if (room[x + 1][y] == 165 || room[x + 1][y] == 125 || room[x + 1][y] == 1005 ) {

							count1++;
							X = x + 1;
							d = 4;
							Y = y;

						} else {
							X = x + 1;
							Y = y;
							d1 = 4;
							room[x + 1][y] = 165;
							count++;

						}
					}

				}
			}
		}

		if (y - 1 >= 0) {
			if (!w.isVisited(cX - 1, cY)) {
				if (room[x][y - 1] != 1 && room[x][y - 1] != 500) {
					if (!(room[x][y - 1] % 20 == 0 && room[x][y - 1] > 0)) {
						if (room[x][y - 1] == 125 || room[x][y - 1] == 165 || room[x][y - 1] == 1005 ) {

							count1++;
							X = x;
							y = y - 1;
							d = 2;

						} else {
							X = x;
							Y = y - 1;
							room[x][y - 1] =165 ;
							d1 = 2;
							count++;
						}
					}
				}
			}
		}
		if (x == 3 && y == 0) {
			arrow(1);
			if (w.hasBreeze(cX, cY) && w.hasStench(cX, cY)) {
				direction(1);
				room[3][1] = 1005;
				room[2][0] = 20;
				room[3][0] = 1;

			} else {
				direction(1);
				room[3][1] = 20;
				room[2][0] = 20;
				room[3][0] = 1;

			}
			return;
		}
		
		if (count1 == 1) {

			
			arrow(d);
			
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					if (room[i][j] == 125 )
						room[i][j] = 1;
					if (room[i][j] ==165 )
						room[i][j] = 20;
				}
			return;

		}
	
		if (count == 1) {
			
			arrow(d1);

			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					if (room[i][j] == 125 )
						room[i][j] = 1;
					if (room[i][j] ==165 )
						room[i][j] = 20;
				}

		} else {
			min=2000;
			path(room, a, x, y, 0, 0,0);
			direction(b[0]);
		}
	}

//-------------------------------------------------------------------------------------------	

	// calculating the probabilites of adjacent blocks are calculated and updated
	// when the box contains breeze.
	public void breezeMethod(int[][] room, int cX, int cY, int x, int y) {

		int count = 0, t1 = 0, d = 0, X = 0, Y = 0;
		int a[] = new int[40];
        if(room[x][y]!=605)
		room[x][y] = 1;
		
		if (x - 1 >= 0) {
			if (!w.isVisited(cX, cY + 1)) {

				if (room[x - 1][y] != 1 && room[x - 1][y] != 605) {

					if (room[x - 1][y] == 125 || room[x - 1][y] ==165 )
						room[x - 1][y] = 40;
					else
						room[x - 1][y] = room[x - 1][y] + 20;

					count++;
					X = x - 1;
					Y = y;
				}

			}
			if (room[x - 1][y] == 605)
				count++;
		}
		if (y + 1 <= 3) {
			if (!w.isVisited(cX + 1, cY)) {
				if (room[x][y + 1] != 1 && room[x][y + 1] != 605) {

					if (room[x][y + 1] == 125 || room[x][y + 1] ==165 )
						room[x][y + 1] = 40;
					else
						room[x][y + 1] = room[x][y + 1] + 20;
					count++;
					X = x;
					Y = y + 1;

				}

			}
			if (room[x][y + 1] == 605)
				count++;
		}
		
		if (x + 1 <= 3) {
			if (!w.isVisited(cX, cY - 1)) {
				if (room[x + 1][y] != 1 && room[x + 1][y] != 605) {

					if (room[x + 1][y] == 125 || room[x + 1][y] ==165 )
						room[x + 1][y] = 40;
					else
						room[x + 1][y] = room[x + 1][y] + 20;
					count++;
					X = x + 1;
					Y = y;
				}

			}
			
			if (room[x + 1][y] == 605)
				count++;
		}
		if (y - 1 >= 0) {
			if (!w.isVisited(cX - 1, cY)) {
				if (room[x][y - 1] != 1 && room[x][y - 1] != 605) {
					if (room[x][y - 1] == 125 || room[x][y - 1] ==165 )
						room[x][y - 1] = 40;
					else
						room[x][y - 1] = room[x][y - 1] + 20;
					count++;
					X = x;
					Y = y - 1;
				}

			}
			if (room[x][y - 1] == 605)
				count++;
		}
		
		if (count == 1) {
			room[X][Y] = 605;
		}
        min=2000;
       
		path(room, a, x, y, 0, 0,0);
		
		direction(b[0]);
		
		

	}

//-------------------------------------------------------------------------------------------	

	// all the probabilities of adjacent boxes are updated when the box contains
	// stench
	public void strenchMethod(int[][] room, int cX, int cY, int x, int y) {

		int count = 0, Y = 0, X = 0, t1 = 0, d = 0, count1 = 0, d1 = 0;

		int[] a = new int[40];
		if(room[x][y]!=605)
		room[x][y] = 1;
		path = 0;
		
		if (x - 1 >= 0) {
			if (!w.isVisited(cX, cY + 1)) {
				if (room[x - 1][y] != 1 ) {
					if (!(room[x - 1][y] % 20 == 0 && room[x - 1][y] > 0 )) {
						if (room[x - 1][y] == 125 || room[x - 1][y] ==165  || room[x - 1][y] == 1005 ) {

							count1++;
							X = x - 1;
							Y = y;

							d = 3;

						} else {
							X = x - 1;
							Y = y;
							room[x - 1][y] = 125;
							d1 = 3;
							count++;
						}
					}
				}
			}
		}

		if (y + 1 <= 3) {
			if (!w.isVisited(cX + 1, cY)) {
				if (room[x][y + 1] != 1 ) {
					if (!(room[x][y + 1] % 20 == 0 && room[x][y + 1] > 0)) {
						if (room[x][y + 1] == 125 || room[x][y + 1] ==165  || room[x][y + 1] == 1005 ) {

							count1++;
							X = x;
							Y = y + 1;

							d = 1;

						} else {
							X = x;
							Y = y + 1;
							d1 = 1;
							room[x][y + 1] = 125;
							count++;
						}
					}
				}
			}

		}
		if (x + 1 <= 3) {
			if (!w.isVisited(cX, cY - 1)) {
				if (room[x + 1][y] != 1 ) {
					if (!(room[x + 1][y] % 20 == 0 && room[x + 1][y] > 0)) {
						if (room[x + 1][y] == 125  || room[x + 1][y] ==165 || room[x + 1][y] == 1005 ) {

							count1++;
							X = x + 1;
							d = 4;
							Y = y;

						} else {
							X = x + 1;
							Y = y;
							d1 = 4;
							room[x + 1][y] = 125;
							count++;

						}
					}

				}
			}
		}

		if (y - 1 >= 0) {
			if (!w.isVisited(cX - 1, cY)) {
				if (room[x][y - 1] != 1 ) {
					if (!(room[x][y - 1] % 20 == 0 && room[x][y - 1] > 0)) {
						if (room[x][y - 1] == 125 || room[x][y - 1] == 165 || room[x][y - 1] == 1005 ) {

							count1++;
							X = x;
							y = y - 1;
							d = 2;

						} else {
							X = x;
							Y = y - 1;
							room[x][y - 1] = 125;
							d1 = 2;
							count++;
						}
					}
				}
			}
		}
		if (x == 3 && y == 0) {
			arrow(1);
			if (w.hasStench(cX, cY)) {
				direction(1);
				room[3][1] = 1;
				room[2][0] = 1005;
				room[3][0] = 1;

			} else {
				direction(1);
				room[3][1] = 1;
				room[2][0] = 1;
				room[3][0] = 1;

			}
			return;
		}
	
		if (count1 == 1) {

			count = 2;
			if(room[x][y]!=605)
			room[x][y] = 1;
			arrow(d);

			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					if (room[i][j] == 125 )
						room[i][j] = 1;
					if (room[i][j] == 165)
						room[i][j] = 20;
				}
			
			return;

		}
		
		if (count == 1) {
			
			if(room[x][y]!=-2)
			room[x][y] = 1;
			arrow(d1);

			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					if (room[i][j] == 125)
						room[i][j] = 1;
					if (room[i][j] == 165)
						room[i][j] = 20;
				}
			

		}
		else
		{
			min=1000;
			
			path(room, a, x, y, 0, 0,0);
			direction(b[0]);
		}

	}

//-------------------------------------------------------------------------------------------	
	/**
	 * Asks your solver agent to execute an action.
	 */

	public void doAction() {

		// Location of the player
		int cX = w.getPlayerX();
		int cY = w.getPlayerY();

		// transformed according to the room array
		int x = 4 - cY;
		int y = cX - 1;

	
		if (path == 1) {
			 
			if (w.hasGlitter(cX, cY)) {
				w.doAction(World.A_GRAB);
				return;
			}
			

			if (k <= (fp))
				direction(b[k]);
			k++;
			if (k == (fp + 1)) {
				path = 0;
				k = 1;
			}
			
		} else {

			if (w.hasStench(cX, cY) && w.isInPit()) {

				if (w.hasGlitter(cX, cY)) {
					w.doAction(World.A_GRAB);
					return;
				}

				room[x][y] = 605;
				w.doAction(World.A_CLIMB);
				   
				strenchMethod(room, cX, cY, x, y);

			}
			else
				if (w.hasBreeze(cX, cY) && w.isInPit() )
				{
					room[x][y]=605;
					if (w.hasGlitter(cX, cY)) {
						w.doAction(World.A_GRAB);
						return;
					}
				
					w.doAction(World.A_CLIMB);
					System.out.println("dfghjk");
					breezeMethod(room, cX, cY, x, y);
				}
				else

			if (w.hasGlitter(cX, cY) && w.hasBreeze(cX, cY)) {
				w.doAction(World.A_GRAB);
				return;
			}
			if (w.hasBreeze(cX, cY) && w.hasStench(cX, cY)) {
				
					breezeAndStrenchMethod(room, cX, cY, x, y);
				
			} else if (w.hasBreeze(cX, cY) ) {
				breezeMethod(room, cX, cY, x, y);
			} else if (w.hasStench(cX, cY)) {
				
				strenchMethod(room, cX, cY, x, y);
			} else if (w.hasGlitter(cX, cY)) {
				w.doAction(World.A_GRAB);
				return;
			} else if (w.isInPit()) {
				
				int[] a = new int[50];
				room[x][y] = 605;
				   
				w.doAction(World.A_CLIMB);
				min=2000;
				
				path(room, a, x, y, 0, 0,0);
				direction(b[0]);
								
			}

			
				
				else
			{

			
				
				int[] a = new int[50];
			
					room[x][y] = 1;

				if (x - 1 >= 0 )
					room[x - 1][y] = 1;
				if (y + 1 <= 3 )
					room[x][y + 1] = 1;
				if (x + 1 <= 3 )
					room[x + 1][y] = 1;
				if (y - 1 >= 0 )
					room[x][y - 1] = 1;
				

				
				
				   min=2000;
					path(room, a, x, y, 0, 0,0);
				  direction(b[0]);
				
			

				

			}
		}

	}

	/**
	 * Genertes a random instruction for the Agent.
	 */
	public int decideRandomMove() {
		return (int) (Math.random() * 4);
	}

}

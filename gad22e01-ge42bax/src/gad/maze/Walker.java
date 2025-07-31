package gad.maze;

public class Walker {
	private Result result;
	private boolean maze[][];


	public Walker(boolean[][] maze, Result result) {
		this.result=result;
		this.maze=maze;



	}


	public boolean walk() {
		int x = 1;
		int y = 0;
		result.addLocation(x, y);
		int direction = 0;
		boolean exit=false;

		while (true) {
			//direction 0 =south
			if (direction == 0) {
				if (!maze[x - 1][y]) {
					x--;
					result.addLocation(x, y);
					direction = 1;

				} else if (!maze[x][y + 1] && maze[x - 1][y]) {
					y++;
					result.addLocation(x, y);
					//direction = 0;
				} else if (!maze[x + 1][y] && maze[x][y + 1] && maze[x - 1][y]) {
					x++;
					result.addLocation(x, y);
					direction = 3;

				} else if (maze[x][y + 1] && maze[x + 1][y] && maze[x - 1][y]) {
					y--;
					result.addLocation(x, y);
					direction = 2;

				}
			}
			//direction 1=west
			else if (direction == 1) {
				if (!maze[x][y - 1]) {
					y--;
					result.addLocation(x, y);
					direction = 2;
				} else if (!maze[x - 1][y] && maze[x][y - 1]) {
					x--;
					result.addLocation(x, y);
					//direction = 1;
				} else if (maze[x - 1][y] && maze[x][y - 1] && !maze[x][y + 1]) {
					y++;
					result.addLocation(x, y);
					direction = 0;
				} else if (maze[x - 1][y] && maze[x][y - 1] && maze[x][y + 1]) {
					x++;
					result.addLocation(x, y);
					direction = 3;
				}

			}

			//direction = 2 north
			else if (direction == 2) {
				if (!maze[x + 1][y]) {
					x++;
					result.addLocation(x, y);
					direction = 3;
				} else if (maze[x + 1][y] && !maze[x][y - 1]) {
					y--;
					result.addLocation(x, y);
					//direction = 2;
				} else if (maze[x + 1][y] && maze[x][y - 1] && !maze[x - 1][y]) {
					x--;
					result.addLocation(x, y);
					direction = 1;
				} else if (maze[x + 1][y] && maze[x][y - 1] && maze[x - 1][y]) {
					y++;
					result.addLocation(x, y);
					direction = 0;
				}

			}
			//going east
			else if (direction == 3) {
				if (!maze[x][y + 1]) {
					y++;
					result.addLocation(x, y);
					direction = 0;
				} else if (x<maze.length-1 && y<maze[0].length-1&&
						maze[x][y + 1] && !maze[x + 1][y]) {
					x++;
					result.addLocation(x, y);
					//direction = 3;
				} else if (x<maze.length-1 && y<maze[0].length-1 && maze[x][y + 1] && maze[x + 1][y] && !maze[x][y - 1]) {
					y--;
					result.addLocation(x, y);
					direction = 2;
				} else if (x<maze.length-1 && y<maze[0].length-1 && maze[x][y + 1] && maze[x + 1][y] && maze[x][y - 1]) {
					x--;
					result.addLocation(x, y);
					direction = 1;

				}

			}



			if(x== maze.length-1 && y== maze[0].length-2){
				exit=true;
				break;
			}else if(x==1 && y==0){
				exit = false;
				break;
			}




		}
		return exit;
	}

	public static void main(String[] args) {
		boolean[][] maze = Maze.generateStandardMaze(10, 10);
		StudentResult result = new StudentResult();
		Walker walker = new Walker(maze, result);
		System.out.println(walker.walk());
		Maze.draw(maze, result);
	}
}

# Game of Life

Testdriven Development (TDD) example of Conway's game of life in Java.

## Preconditions:

Rules of Conway's life:
 1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
 2. Any live cell with two or three live neighbours lives on to the next generation.
 3. Any live cell with more than three live neighbours dies, as if by overcrowding.
 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The field is represented as single string using a space as newline seperator. 
The fieldsize is fixed (can not grow).

## Algorithm

Using the TTD approach as advocated by Robert C. Martin (aka. uncle Bob) the main algorithm comes down to:

```java
	public boolean cellWillLive(String[] lines, int x, int y) {
		int neighbours = numberOfLivingNeighbours(lines, x, y);
		if (cellIsAlive(lines[y], x))
			return neighbours > 2 && neighbours < 5;		
		else
			return neighbours == 3;
	}
```			

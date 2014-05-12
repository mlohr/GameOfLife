package gameoflife;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {
	
	private static final String NEW_LINE = " ";
	private static final String LIVING_CELL = "x";
	private static final String DEAD_CELL = ".";
	
	public void assertNextIteration(String current, String expected) {
		assertEquals(expected, nextIteration(current));
	}

	@Test
	public void nullTest() {
		assertNextIteration(null, "");
	}

	@Test
	public void deadCellsStayDead() throws Exception {
		assertNextIteration(DEAD_CELL, DEAD_CELL);
		assertNextIteration("...", "...");
		assertNextIteration("... ...", "... ...");
	}

	@Test
	public void testIterations() throws Exception {
		assertNextIteration(LIVING_CELL, DEAD_CELL);
		assertNextIteration("xxx", ".x.");
		assertNextIteration("xxxx", ".xx.");
		assertNextIteration("xxx.xxx", ".x...x.");
		assertNextIteration("... xxx ...", ".x. .x. .x.");
		assertNextIteration(".x. .x. .x.", "... xxx ...");
	}

	@Test
	public void testStillLifes() throws Exception {
		final String block = "xx xx";
		assertNextIteration(block, block);
		final String beehive = ".xx. x..x .xx.";
		assertNextIteration(beehive, beehive);
	}
	private String nextIteration(String iteration) {
		String result = "";
		if (iteration == null) 
			return result;
		final String[] lines = iteration.split(NEW_LINE);
		for (int y=0; y < lines.length; y++)	{			
			for(int x=0; x < lines[y].length(); x++) {
				result += cellWillLive(lines, x, y) ? LIVING_CELL : DEAD_CELL;
			}
			result += NEW_LINE;
		}
		return result.trim();
	}

	public boolean cellWillLive(String[] lines, int x, int y) {
		int neighbours = numberOfLiveNeighbours(lines, x, y);
		if (cellIsAlive(lines[y], x))
			return neighbours > 2;		
		else
			return neighbours == 3;
	}

	public int numberOfLiveNeighbours(String[] lines, int x, int y) {
		int neighbours = countLiveNeighboursInRow(lines, x, y);
		if (y>0)
			neighbours += countLiveNeighboursInRow(lines, x, y-1);
		if (y<lines.length-1) {
			neighbours += countLiveNeighboursInRow(lines, x, y+1);
		}
		return neighbours;
	}

	public int countLiveNeighboursInRow(String[] lines, int x, int y) {
		int neighbours = cellIsAlive(lines[y], x) ? 1 : 0;
		neighbours += x>0 && cellIsAlive(lines[y], x-1) ? 1 : 0;
		neighbours += x<lines[y].length()-1 && cellIsAlive(lines[y], x+1) ? 1 : 0;
		return neighbours;
	}

	public boolean cellIsAlive(String field, int i) {
		return field.substring(i, i+1).equals(LIVING_CELL);
	}

}

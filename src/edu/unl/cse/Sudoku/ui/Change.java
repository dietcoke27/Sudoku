package edu.unl.cse.Sudoku.ui;

public class Change {
int prevVal;
int row;
int col;

public void setPrevVal(int prev)
{
	prevVal = prev;
}
public void setRow(int r)
{
	row = r;
}
public void setCol(int c)
{
	col = c;
}
public int getPrevVal()
{
	return prevVal;
}
public int getRow()
{
	return row;
}
public int getCol()
{
	return col;
}
}

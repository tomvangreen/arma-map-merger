package sqmmerge.model

import sqmmerge.model.attribute.AttributeType

interface MissionReader {
	public boolean eof()
	public boolean lastLine()
	public void setRow(int row)
	public int getRow()
	public String getLine()
	public String getLineUntrimmed()
	public String nextLine()
	public void left()
	public void right()
	public void err(String message)
	public void err(String message, boolean throwException)
	public void info(String message)
	public AttributeType<?> findAttributeType()
}

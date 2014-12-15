package sqmmerge.model

import sqmmerge.util.Indent

class Reader {
	private final String content
	private final String[] lines
	public final Indent indent = new Indent('  ')
	private int row

	public Reader(String content){
		this.content = content
		this.lines = content.split('\n')
	}

	public boolean eof(){
		row >= lines.length
	}
	public boolean lastLine(){
		//TODO: This is a hack. Better properly trim the document beforehand...
		row >= lines.length -2
	}

	public void setRow(int row){
		this.row = row
	}

	public int getRow(){
		row
	}

	String getLine(){
		getLineUntrimmed().trim()
	}

	String getLineUntrimmed(){
		(row >= 0 && !eof()) ? lines[row] : ""
	}

	String nextLine(){
		row++
		//		println('' + row + '#' + getLine())
		getLine()
	}

	void left(){
		indent.left()
	}

	void right(){
		indent.right()
	}

	void err(String message, boolean throwException = true){
		System.err.println("L" + (row + 1) + ":\t"  + indent + message)
		if(throwException){
			throw new RuntimeException(message)
		}
	}

	void info(String message){
		println("L" + (row + 1) + ":\t" + indent + message)
	}
}

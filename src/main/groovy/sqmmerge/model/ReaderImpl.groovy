package sqmmerge.model

import sqmmerge.model.attribute.AttributeType
import sqmmerge.model.attribute.KeyValueType
import sqmmerge.util.Indent

class ReaderImpl implements MissionReader{
	private final String content
	private final String[] lines
	public final Indent indent = new Indent('  ')
	private int row
	public List<AttributeType> registeredTypes = new ArrayList<AttributeType>()

	public ReaderImpl(String content){
		this.content = content
		this.lines = content.split('\n')
		registeredTypes.add(new KeyValueType())
	}

	@Override
	public boolean eof(){
		row >= lines.length
	}

	@Override
	public boolean lastLine(){
		//TODO: This is a hack. Better properly trim the document beforehand...
		row >= lines.length -2
	}

	@Override
	public void setRow(int row){
		this.row = row
	}

	@Override
	public int getRow(){
		row
	}

	@Override
	public 	String getLine(){
		getLineUntrimmed().trim()
	}

	@Override
	public String getLineUntrimmed(){
		(row >= 0 && !eof()) ? lines[row] : ""
	}

	@Override
	public 	String nextLine(){
		row++
		//		println('' + row + '#' + getLine())
		getLine()
	}

	@Override
	public void left(){
		indent.left()
	}

	@Override
	public void right(){
		indent.right()
	}

	@Override
	public void err(String message){
		err(message, false)
	}
	@Override
	public void err(String message, boolean skipException){
		System.err.println("L" + (row + 1) + ":\t"  + indent + message)
		if(!skipException){
			throw new RuntimeException(message)
		}
	}

	@Override
	public void info(String message){
		println("L" + (row + 1) + ":\t" + indent + message)
	}

	@Override
	public AttributeType<?> findAttributeType(){
		for(AttributeType<?> type : registeredTypes){
			if(type.check(this)){
				return type
			}
		}
		null
	}
}

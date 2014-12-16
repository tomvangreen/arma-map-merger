package sqmmerge.model

import sqmmerge.util.Indent

public class Writer {
	private final StringBuilder builder = new StringBuilder()
	private final Indent indent = new Indent("\t")


	public static enum Control{
		Indent, NewLine, Next, Right, Left
	}

	public void clear(){
		builder.setLength(0)
		indent.reset()
	}

	public String flush(){
		String output = builder.toString()
		clear()
		output
	}

	public Writer leftShift(Object parameter){
		if(parameter instanceof Control){
			switch(parameter as Control){
				case Control.Indent:
					builder << indent
					break;
				case Control.NewLine:
					builder << '\r\n'
					break;
				case Control.Next:
					builder << '\r\n' << indent
					break;
				case Control.Left:
					indent.left()
					break;
				case Control.Right:
					indent.right()
					break;
				default:
					break;
			}
		}
		else{
			builder << parameter
		}
		this
	}
}

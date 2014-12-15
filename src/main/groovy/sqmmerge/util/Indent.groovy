package sqmmerge.util;

public class Indent {
	public String indent
	public final String base

	public Indent(String base) {
		this.indent = ""
		this.base = base
	}

	def right(){
		indent = indent + base
	}

	def left() {
		if(indent.length() > 1){
			indent = indent.substring(base.length())
		}
		else if(indent.length() == 1){
			indent = ""
		}
	}

	def reset(){
		indent = ""
	}

	@Override
	public String toString(){
		return indent
	}
}

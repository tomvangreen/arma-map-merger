package sqmmerge;

import java.util.regex.Pattern

import sqmmerge.model.MissionSet
import sqmmerge.model.Node
import sqmmerge.util.Indent



public class Integrator {
	public MissionSet result;
	Pattern listPattern = ~/items=([0-9]*);/


	public final static String INDENT_BASE = "\t"
	public final Indent indent = new Indent("  ")

	void err(String message, boolean throwException = true){
		System.err.println(indent + message)
		if(throwException){
			throw new RuntimeException(message)
		}
	}

	void info(String message){
		println(indent + message)
	}

	void warn(String message){
		println('Warning: ' + indent + message)
	}

	public void integrate(Configuration config ){
		//		loader.debug = false
		for(MissionConfiguration mission : config.missions){
			String content = new File(mission.file).text
			MissionSet set = new MissionSet()
			set.load(content)
			if(result == null){
				result = set
			}
			else{
				result.integrate(set, this)
			}
		}
		new File(config.outputFile).write(result.toString())
	}

	public Node<? extends Node> integrate(Node<? extends Node> existing, Node<? extends Node> other){
		if(other == null) {
			return existing
		}
		else if(existing == null){
			return other
		}
		existing.integrate(other, this)
		return existing
	}


}
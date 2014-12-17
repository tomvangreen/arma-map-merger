package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control



public class Intel implements Node<Intel>{
	public KeyValue timeOfChanges
	public KeyValue startWeather
	public KeyValue startWind
	public KeyValue startWindDir
	public KeyValue startLightnings
	public KeyValue startWaves
	public KeyValue forecastWeather
	public KeyValue forecastWind
	public KeyValue forecastWaves
	public KeyValue startGust
	public KeyValue forecastLightnings
	public KeyValue year
	public KeyValue month
	public KeyValue day
	public KeyValue hour
	public KeyValue minute
	public KeyValue startFogDecay
	public KeyValue forecastFogDecay	public KeyValue rainForced	public KeyValue lightningsForced
	public KeyValue wavesForced
	public KeyValue windForced
	@Override
	public List<Node> getChildren() {
		def list = new ArrayList<Node>()
		list.addAll([timeOfChanges, startWeather, startWind, startWaves, forecastWind, forecastLightnings, year, month, day, hour, minute, startFogDecay, forecastFogDecay])
		return list;
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.nextLine()
		reader.info("Load Intel")
		if(!"{".equals(line)){
			reader.err("Expected '{'")
		}

		def intel = new Intel()

		line = reader.nextLine()

		while(!"};".equals(line)){
			loadIntelProperty(line, reader)
			line = reader.nextLine()
		}
		reader.nextLine()
		intel
	}

	def loadIntelProperty(String line, MissionReader reader){
		if(line.startsWith("timeOfChanges=")){
			timeOfChanges = new KeyValue(line)
		}
		else if(line.startsWith("startWeather=")){
			startWeather = new KeyValue(line)
		}
		else if(line.startsWith("startWind=")){
			startWind = new KeyValue(line)
		}
		else if(line.startsWith("startWaves=")){
			startWaves = new KeyValue(line)
		}
		else if(line.startsWith("forecastWeather=")){
			forecastWeather = new KeyValue(line)
		}
		else if(line.startsWith("forecastWind=")){
			forecastWind = new KeyValue(line)
		}
		else if(line.startsWith("forecastWaves=")){
			forecastWaves = new KeyValue(line)
		}
		else if(line.startsWith("forecastLightnings=")){
			forecastLightnings = new KeyValue(line)
		}
		else if(line.startsWith("year=")){
			year = new KeyValue(line)
		}
		else if(line.startsWith("month=")){
			month = new KeyValue(line)
		}
		else if(line.startsWith("day=")){
			day = new KeyValue(line)
		}
		else if(line.startsWith("hour=")){
			hour = new KeyValue(line)
		}
		else if(line.startsWith("minute=")){
			minute = new KeyValue(line)
		}
		else if(line.startsWith("startFogDecay=")){
			startFogDecay = new KeyValue(line)
		}
		else if(line.startsWith("forecastFogDecay=")){
			forecastFogDecay = new KeyValue(line)
		}
		else if(line.startsWith("startWindDir=")){
			startWindDir = new KeyValue(line)
		}
		else if(line.startsWith("startLightnings=")){
			startLightnings = new KeyValue(line)
		}
		else if(line.startsWith("startGust")){
			startGust = new KeyValue(line)
		}
		else if(line.startsWith("rainForced=")){
			rainForced = new KeyValue(line)
		}
		else if(line.startsWith("lightningsForced=")){
			lightningsForced = new KeyValue(line)
		}
		else if(line.startsWith("wavesForced=")){
			wavesForced = new KeyValue(line)
		}
		else if(line.startsWith("windForced=")){
			windForced = new KeyValue(line)
		}
		else{
			reader.err('Intel: Unknown Property: ', true)
			reader.err(line)
		}
	}


	@Override
	public void write(Writer writer) {
		writer << Control.Next << 'class Intel'
		writer << Control.Next << '{'
		writer << Control.Right

		timeOfChanges?.write(writer)
		startWeather?.write(writer)
		startWind?.write(writer)
		startWindDir?.write(writer)
		startLightnings?.write(writer)
		startWaves?.write(writer)
		startGust?.write(writer)
		forecastWeather?.write(writer)
		forecastWind?.write(writer)
		forecastWaves?.write(writer)
		forecastLightnings?.write(writer)
		rainForced?.write(writer)
		lightningsForced?.write(writer)
		wavesForced?.write(writer)
		windForced?.write(writer)
		year?.write(writer)
		month?.write(writer)
		day?.write(writer)
		hour?.write(writer)
		minute?.write(writer)
		startFogDecay?.write(writer)
		forecastFogDecay?.write(writer)

		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Intel node, Integrator integrator) {
		// TODO Auto-generated method stub

	}
}

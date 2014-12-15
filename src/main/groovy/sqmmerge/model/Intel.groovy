package sqmmerge.model

import sqmmerge.model.Writer.Control


public class Intel implements Node{
	public KeyValue timeOfChanges
	public KeyValue startWeather
	public KeyValue startWind
	public KeyValue startWaves
	public KeyValue forecastWeather
	public KeyValue forecastWind
	public KeyValue forecastWaves
	public KeyValue forecastLightnings
	public KeyValue year
	public KeyValue month
	public KeyValue day
	public KeyValue hour
	public KeyValue minute
	public KeyValue startFogDecay
	public KeyValue forecastFogDecay
	@Override
	public List<Node> getChildren() {
		def list = new ArrayList<Node>()
		list.addAll([
			timeOfChanges,
			startWeather,
			startWind,
			startWaves,
			forecastWind,
			forecastLightnings,
			year,
			month,
			day,
			hour,
			minute,
			startFogDecay,
			forecastFogDecay
		])
		return list;
	}

	@Override
	public void read(Reader reader) {
		def line = reader.nextLine()
		reader.info("Load Intel")
		if(!"{".equals(line)){
			reader.err("Expected '{'")
		}

		def intel = new Intel()

		line = reader.nextLine()

		while(!"};".equals(line)){
			loadIntelProperty(line)
			line = reader.nextLine()
		}
		reader.nextLine()
		intel
	}

	def loadIntelProperty(String line){
		if(line.startsWith("timeOfChanges=")){
			timeOfChanges = new KeyValue(line)
		}
		if(line.startsWith("startWeather=")){
			startWeather = new KeyValue(line)
		}
		if(line.startsWith("startWind=")){
			startWind = new KeyValue(line)
		}
		if(line.startsWith("startWaves=")){
			startWaves = new KeyValue(line)
		}
		if(line.startsWith("forecastWeather=")){
			forecastWeather = new KeyValue(line)
		}
		if(line.startsWith("forecastWind=")){
			forecastWind = new KeyValue(line)
		}
		if(line.startsWith("forecastWaves=")){
			forecastWaves = new KeyValue(line)
		}
		if(line.startsWith("forecastLightnings=")){
			forecastLightnings = new KeyValue(line)
		}
		if(line.startsWith("year=")){
			year = new KeyValue(line)
		}
		if(line.startsWith("month=")){
			month = new KeyValue(line)
		}
		if(line.startsWith("day=")){
			day = new KeyValue(line)
		}
		if(line.startsWith("hour=")){
			hour = new KeyValue(line)
		}
		if(line.startsWith("minute=")){
			minute = new KeyValue(line)
		}
		if(line.startsWith("startFogDecay=")){
			startFogDecay = new KeyValue(line)
		}
		if(line.startsWith("forecastFogDecay=")){
			forecastFogDecay = new KeyValue(line)
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
		startWaves?.write(writer)
		forecastWeather?.write(writer)
		forecastWind?.write(writer)
		forecastWaves?.write(writer)
		forecastLightnings?.write(writer)
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
}

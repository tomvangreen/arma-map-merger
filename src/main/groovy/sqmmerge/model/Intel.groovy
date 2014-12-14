package sqmmerge.model


public class Intel implements Node{
	public KeyValue timeOfChanges
	public KeyValue startWeather
	public KeyValue startWind
	public KeyValue startWaves
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
}

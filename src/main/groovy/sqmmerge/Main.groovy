package sqmmerge

import sqmmerge.model.MissionSet

public class Main{
	public final static boolean debug = false;
	public static void main(String[] args){
		testMerge()
		//		testLoader()
	}

	private static testMerge() {

		//		def input =[
		//			'data/merge-src-1.sqm'
		//			,
		//			'data/merge-src-2.sqm'
		//		]
		def input =[
			'data/huntingparty_respawn.Altis/mission.sqm'
			,
			'data/huntingparty_bomos.Altis/mission.sqm'
			,
			'data/zet-huntingparty_oreokastro.Altis/mission.sqm'
			,
			'data/zet-huntingparty_thronos.Altis/mission.sqm'
		]


		def config = new Configuration()
		config.outputFile = 'data/huntingparty.sqm.generated'
		def master = input.first()
		input.each{
			config.missions.add(new MissionConfiguration(file: it, isMaster: master.equals(it)))
		}
		def integrator = new Integrator()
		integrator.integrate(config)
	}

	private static testLoader() {
		String file = 'many-things.sqm'
		println('Loader:')
		String fileContents = new File('data/' + file).text
		MissionSet set = new MissionSet()
		set.load(fileContents)
		println("Output:")
		String output = set.toString()
		println(output)

		new File('data/' + file + '.generated').write(output)
	}
}
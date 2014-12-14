package sqmmerge

public class Main{
	public static void main(String[] args){
		//		testMerge()
		testLoader2()
	}

	private static testMerge() {

		def input =[
			'data/merge-src-1.sqm'
			,
			'data/merge-src-2.sqm'
		]


		def config = new Configuration()
		config.outputFile = 'data/merge-result.sqm.generated'
		def master = input.first()
		input.each{
			config.missions.add(new MissionConfiguration(file: it, isMaster: master.equals(it)))
		}
		def integrator = new Integrator()
		integrator.integrate(config)
	}

	private static testLoader() {
		String file = 'merge-src-2.sqm'
		println('Loader:')
		String fileContents = new File('data/' + file).text
		def loader = new Loader()
		def data = loader.load(fileContents)

		println("Output:")
		def writer = new Writer()
		String output = writer.write(data)
		println(output)

		new File('data/' + file + '.generated').write(output)
	}
	private static testLoader2() {
		String file = 'merge-src-2.sqm'
		println('Loader:')
		String fileContents = new File('data/' + file).text
		def loader = new sqmmerge.model.Loader()
		def data = loader.load(fileContents)

		println("Output:")
		def writer = new Writer()
		String output = writer.write(data)
		println(output)

		new File('data/' + file + '.generated').write(output)
	}
}
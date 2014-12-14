package sqmmerge.test
import org.junit.Test

import sqmmerge.Loader
import sqmmerge.Writer


public class LoaderBenchmarks{
	private void runTestOnMap(String mapFile){
		println("Loader: " + mapFile)
		String fileContents = new File('data/' + mapFile).text
		def loader = new Loader()
		def data = loader.load(fileContents)

		println("Output: " + mapFile)
		def writer = new Writer()
		String output = writer.write(data)
		println(output)

		new File('data/' + mapFile + '.generated').write(output)
	}

	@Test
	public def testSmallMap1(){
		runTestOnMap('biggermission.sqm')
	}
}
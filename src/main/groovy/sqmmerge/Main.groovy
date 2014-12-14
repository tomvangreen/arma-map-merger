package sqmmerge

public class Main{
	public static void main(String[] args){
		String file = 'mission.sqm'
		println("Loader:")
		String fileContents = new File('data/' + file).text
		def loader = new Loader()
		def data = loader.load(fileContents)

		println("Output:")
		def writer = new Writer()
		String output = writer.write(data)
		println(output)

		new File('data/' + file + '.generated').write(output)
	}
}

public class Main{
	public static void main(String[] args){
		println("Loader:")
		String fileContents = new File('data/mission.sqm').text
		def loader = new Loader()
		def data = loader.load(fileContents)

		println("Output:")
		def writer = new Writer()
		String output = writer.write(data)
		println(output)

		new File('data/mission.sqm.generated').write(output)
	}
}
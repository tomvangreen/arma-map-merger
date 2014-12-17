package sqmmerge

import sqmmerge.model.ReaderImpl
import sqmmerge.model.attribute.KeyValueType


class MissionReaderTest extends GroovyTestCase {
	void "test discover KeyValueType"(){
		def reader = new ReaderImpl(KeyValueTypeTest.VALID_KEYVALUE_LINES)

		println("MissionReaderTest: test discover KeyValueType")
		while(!reader.eof()){
			def line = reader.getLine()
			println('  Case: ')
			println('    ' + line)
			assertEquals(true, reader.findAttributeType() instanceof KeyValueType)
			reader.nextLine()
		}
	}
}
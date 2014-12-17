package sqmmerge

import sqmmerge.model.ReaderImpl
import sqmmerge.model.attribute.KeyValueType

class KeyValueTypeTest extends GroovyTestCase {
	public final static String VALID_KEYVALUE_LINES = "bla=yada;\ntibau=gurn;\nenLustigeString='dasisch;entest';"
	public final static String INVALID_KEYVALUE_LINES = "bla=yada\nclass Bla\nenLustigeString[]='dasisch;\nentest'"

	void "test positive checks"(){
		def reader = new ReaderImpl(VALID_KEYVALUE_LINES)
		def type = new KeyValueType()

		println('KeyValueTypeTest: test positive checks')
		while(!reader.eof()){
			println("  Case: ")
			println("    " + reader.getLine())
			assertEquals(true, type.check(reader))
			reader.nextLine()
		}
	}

	void "test negative checks"(){
		def reader = new ReaderImpl(INVALID_KEYVALUE_LINES)
		def type = new KeyValueType()

		println('KeyValueTypeTest: test negative checks')
		while(!reader.eof()){
			println("  Case: ")
			println("    " + reader.getLine())
			assertEquals(false, type.check(reader))
			reader.nextLine()
		}
	}
}
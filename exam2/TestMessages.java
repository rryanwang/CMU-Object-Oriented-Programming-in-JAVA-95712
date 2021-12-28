package exam2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMessages {
	
	Message message1 = new Caesar();
	Message message2 = new Keyword();
	
	//Caeser Tests

	@Test
	public void test1_nonWrapCaesarEncryption() {
		message1.setMessage("hello", "3");
		String encrypted = ((Encryptable)message1).encrypt();
		assertEquals("khoor", encrypted);
		
	}
	
	@Test
	public void test2_nonWrapCaesarDecryption() {
		message1.setMessage("khoor", "3");
		String decrypted = ((Encryptable)message1).decrypt();
		assertEquals("hello", decrypted);
	}
	
	@Test
	public void test3_wrapCaesarEncryption() {
		message1.setMessage("xyz", "3");
		String encrypted = ((Encryptable)message1).encrypt();
		assertEquals("abc", encrypted);
		
	}
	
	@Test
	public void test4_wrapCaesarDecryption() {
		message1.setMessage("abc", "3"); 
		String decrypted = ((Encryptable)message1).decrypt();
		assertEquals("xyz", decrypted);
	}
	
	@Test
	public void test5_caesarWithSpacesEncryption() {
		message1.setMessage("hello there", "7");
		String encrypted = ((Encryptable)message1).encrypt();
		assertEquals("olssv aolyl", encrypted);
	}
	
	@Test
	public void test6_caesarWithSpacesDecryption() {
		message1.setMessage("olssv aolyl", "7");
		String decrypted = ((Encryptable)message1).decrypt();
		assertEquals("hello there", decrypted);
	}

	// Keyword tests
	
	@Test
	public void test7_nonWrapKeywordEncryption() {
		message2.setMessage("abc", "abc");
		String encrypted = ((Encryptable)message2).encrypt();
		assertEquals("bdf", encrypted);
		
	}
	
	@Test
	public void test8_nonWrapKeywordDecryption() {
		message2.setMessage("bdf", "abc");
		String decrypted = ((Encryptable)message2).decrypt();
		assertEquals("abc", decrypted);
		
	}
	
	@Test
	public void test9_wrapKeywordEncryption() {
		message2.setMessage("xyz", "abc");
		String encrypted = ((Encryptable)message2).encrypt();
		assertEquals("yac", encrypted);
		
	}
	@Test
	public void test10_wrapKeywordDecryption() {
		message2.setMessage("yac", "abc");
		String decrypted = ((Encryptable)message2).decrypt();
		assertEquals("xyz", decrypted);
	}
	
	@Test
	public void test11_keywordWithSpacesEncryption() {
		message2.setMessage("hello there", "abc");
		String encrypted = ((Encryptable)message2).encrypt();
		assertEquals("igomq ujhsg", encrypted);
	}
	
	@Test
	public void test12_keywordWithSpacesDecryption() {
		message2.setMessage("igomq ujhsg", "abc");
		String decrypted = ((Encryptable)message2).decrypt();
		assertEquals("hello there", decrypted);
	}
	

}

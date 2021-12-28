//Zhenxi Wang zhenxiw
package exam2;

import java.lang.StringBuilder;
public class Keyword extends Message{
	@Override
	public String encrypt() {
		char[] temp = this.key.toCharArray();
		int[] mask = new int[temp.length];
		for (int i = 0; i < temp.length; i++){
			mask[i] = (int) temp[i];
		}
		for (int i = 0; i < this.key.length(); i++){
			mask[i] = mask[i] - 'a' + 1;
		}

		StringBuilder result = new StringBuilder();
		int p = 0;
		for (char i: this.message.toCharArray()){
			if (i == Encryptable.SPACE){
				result.append(" ");

			} else {
				int newc = (i + mask[p]);
				if (newc > 'z'){
					newc -= 26;
				}
				result.append((char)newc);
			}

			p ++;
			if (p >= this.key.length()){
				p -= this.key.length();
			}
		}
		return result.toString();
	}

	@Override
	public String decrypt() {
		char[] temp = this.key.toCharArray();
		int[] mask = new int[temp.length];
		for (int i = 0; i < temp.length; i++){
			mask[i] = (int) temp[i];
		}
		for (int i = 0; i < this.key.length(); i++){
			mask[i] = mask[i] - 'a' + 1;
		}

		StringBuilder result = new StringBuilder();
		int p = 0;
		for (char i: this.message.toCharArray()){
			if (i == Encryptable.SPACE){
				result.append(" ");
			} else {
				int newc = (i - mask[p]);
				if (newc < 'a'){
					newc += 26;
				}
				result.append((char)newc);
			}

			p ++;
			if (p >= this.key.length()){
				p -= this.key.length();
			}
		}
		return result.toString();
	}
}
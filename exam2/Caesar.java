//Zhenxi Wang zhenxiw
package exam2;

import java.lang.StringBuilder;
public class Caesar extends Message{
	
	@Override
	public String encrypt() {
        int mask = Integer.valueOf(this.key).intValue();
		StringBuilder result = new StringBuilder();

        for (char i: this.message.toCharArray()){
            if (i == Encryptable.SPACE){
                result.append(" ");
                continue;
            }
            int newc = i + mask;
            if (newc > 'z'){
                newc -= 26;
            }
            result.append((char)newc);
        }

		return result.toString();
	}

	@Override
	public String decrypt() {
        int mask = Integer.valueOf(this.key).intValue();
		StringBuilder result = new StringBuilder();

        for (char i: this.message.toCharArray()){
            if (i == Encryptable.SPACE){
                result.append(" ");
                continue;
            }
            int newc = i - mask;
            if (newc < 'a'){
                newc += 26;
            }
            result.append((char)newc);
        }

		return result.toString();
	}

}

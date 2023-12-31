package com.CI.attendance.Service;
import org.passay.EnglishCharacterData;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

@Service
public class RandomPasswordGenerator {

	public String generatePassayPassword() {
	    PasswordGenerator gen = new PasswordGenerator();
	    EnglishCharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);

	    EnglishCharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);

	    EnglishCharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);

		
	    CharacterData specialChars = new CharacterData() {
	        public String getErrorCode() {
	            return " ";
	        }

	        public String getCharacters() {
	            return "!@#$%^&*()_+";
	        }
	    };
		  
	    CharacterRule splCharRule = new CharacterRule(specialChars);
	    splCharRule.setNumberOfCharacters(2);

	    String password = gen.generatePassword(8, splCharRule, lowerCaseRule, 
	      upperCaseRule, digitRule);
	    return password;
	}
	 
}

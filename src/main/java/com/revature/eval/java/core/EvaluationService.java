package com.revature.eval.java.core;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 *
	 * Replace all non letters with spaces
	 * Then disregard spaces to get first letter of word
	 * bring back to upper case
	 * @param phrase
	 * @return
	 * \w works for [^a-zA-Z]
	 * \\w+
	 */
	public String acronym(String phrase) {
		String acronyms = phrase.replaceAll("\\W+" , " ");
		String delimiters = "\\s+";
		String[] acro = acronyms.split(delimiters);
		
		//.isWhitespace or \\s+ whitespace regex
		String newAcro = "";
		
		// colon means for each loop
		//The for-each loop, introduced in release 1.5, gets rid of the clutter and the opportunity for 
		//error by hiding the iterator or index variable completely. The resulting idiom applies equally to collections and arrays:
		//The preferred idiom for iterating over collections and arrays
		for (String acroString: acro) {
			if(!acroString.equals("\\s+")) {
				newAcro += acroString.charAt(0);
			}
		}
			return newAcro.toUpperCase();

	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			double sideOne = getSideOne();
			double sideTwo = getSideTwo();
			double sideThree = getSideThree();
			if((sideOne == sideTwo) && (sideTwo == sideThree) && (sideOne == sideThree)) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles(){
			double sideOne = getSideOne();
			double sideTwo = getSideTwo();
			double sideThree = getSideThree();
			if ((sideOne == sideTwo) || (sideTwo == sideThree) || (sideOne == sideThree)) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			double sideOne = getSideOne();
			double sideTwo = getSideTwo();
			double sideThree = getSideThree();
			if((sideOne != sideTwo) && (sideTwo != sideThree) && (sideOne != sideThree)) {
				//System.out.println("scalene");
				return true;
			}
			return false;
		}

	}	


	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 *
	 * map
	 *
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int score = 0;
    	String cap = string.toUpperCase();
		String[] word = cap.split("");
		for (String str: word) {
			if(str.matches("[AEIOULNRST]")){
				score += 1;
			}
			if(str.matches("[DG]")) {
				score += 2;
			}
			if(str.matches("[BCMP]")) {
				score += 3;
			}
			if(str.matches("[FHVWY]")) {
				score += 4;
			}
			if(str.matches("K")) {
				score += 5;
			}
			if(str.matches("[JX]")) {
				score += 8;
			}
			if(str.matches("[QZ]")){
				score += 10;
			}
		}
			return score;
	
    }
		
	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253, 613-995-0253, 1 613 995 0253, 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 * 
	 * String is passed through--all non-numbers are replaced and if resulting string is
	 * greater than 11 or less than 10 exception is thrown, then an if statement for if length at 0 is 1 to replace
	 * it. then print it 
	 */
	public String cleanPhoneNumber(String string) {
			String phone = string.replaceAll("\\D+", ""); //cleans string of stuff
			if(phone.charAt(0) == 1) { //replaces first instance of 1 if in 1st position
				 String newPhone = phone.replaceFirst("1", "");
				 if ((newPhone.length() == 10))  
					throw new IllegalArgumentException();
				 else {
					 return newPhone;
				 }
			} else if(phone.charAt(0) != 1 && phone.length() == 10)
						return phone;
						else {
							throw new IllegalArgumentException();
			}
				
		}	
		
	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 * ContainsKey method is used to check whether a particular key is being mapped into the HashMap or not. 
	 * It takes the key element as a parameter and returns True if that element is mapped in the map.
	 * 
	 */
	public Map<String, Integer> wordCount(String string) {
		String clean = string.replaceAll("[.!?\\-,]", " ").toLowerCase(); //get it as a space
		Map<String, Integer> phrase = new HashMap<String, Integer>();
		String[] word = clean.split("\\s+");
			for (String key: word) {
				if (phrase.containsKey(key)) {
	                phrase.put(key, phrase.get(key) + 1);
	            } else {
	                phrase.put(key, 1);
	            }
			}
				return phrase;
		}
		

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			
			return Arrays.binarySearch(sortedList.toArray(), t);
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. 
	 * Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String pigLatin = null;
		String piggy = string.toLowerCase();
		String[] pig = piggy.split("\\s+");
		for (String word : pig) {
			if (word.matches("^y[aeiou].*$")) {
				word = word.substring(1,word.length())+ "yay";
			} else if (word.matches("^[aeiou].*")) {
				word += "ay";
			} else {
				Pattern pattern = Pattern.compile("(.*?[aeiouy]).*");
				Matcher matcher = pattern.matcher(word);
				matcher.find();
				String latin = matcher.group(1);

				if (!latin.endsWith("qu")) {
					latin = latin.substring(0, latin.length() - 1);
				}
				word = word.substring(latin.length()) + latin + "ay";
			}
			pigLatin = (pigLatin == null) ? word : pigLatin + " " + word;
		}

		return pigLatin;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 
	 * 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 
	 * 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190
	 *  Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String number = String.valueOf(input); //Separate input so put it into a string
        int a = 0;
        for (int i = 0; i < number.length(); i++) { //need to iterate over string and grab the character and transform it back
            int digit = Character.digit(number.charAt(i), 10); //radix 10--the base number to consider in converting the String object
            //System.out.println(digit);
            a += Math.pow(digit, number.length()); //each digit to the power of added together
        }
      
        if(input==a){ //if armstrong number 
            return true;
        }
        return false;
	}
		

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		
		List<Long> list = new ArrayList<Long>();
		while(l%2==0) {
			list.add(2l);
			l=l/2;
		}for(long i=3; i<1/2;i+=2) {
			while(l%i==0) {
				list.add(i);
				l=l/1;
			}
		}if(l>2) {
			list.add(l);
		}
		return list;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}
		
		public String rotate(String string) {
			
			char[] cipher = new char[string.length()];
			String result = "";
			
				 for (int i=0; i<string.length(); i++) 
			        { 
					 if (Character.isLetter(string.charAt(i))) {
						 	if (Character.isUpperCase(string.charAt(i))) { 
						 		char ch = (char)(((int)string.charAt(i) + key - 65) % 26 + 65); 
						 		cipher[i] = ch; 
						 	} else { 
						 		char ch = (char)(((int)string.charAt(i) + key - 97) % 26 + 97); 
						 		cipher[i] = ch;  
						 		} 
				        	} else {
				        		char ch = (char)(((int)string.charAt(i)));
				        		cipher[i] = ch;  
				        	}
				
			        	}
				 
				 for(int j = 0; j<cipher.length;j++) {
					 	result = result + cipher[j];
					 }
				 
				 System.out.println(result);
				 return result;
	}
		//need to rotate numbers and capital letters

//		public String rotate(String string) {
//			char[] c = string.toCharArray();
//	    	for(int i: c) {
//	    		if (!Character.isLetterOrDigit(i)) 
//		                continue;
//	    		if(Character.isLetter(c[i])) {
//	    			if(Character.isUpperCase(c[i])) {
//	    				c[i] = (char)('A' + (c[i] - 'A' + key) % 26); //we be doing the same as with the other cipher
//	    			} else {
//	    				c[i] = (char)('a' + (c[i] - 'a' + key) % 26);
//	    			}
//	    		}
//	    	}
//	    	return new String(c);
//		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 *intStream??
	 */
	public int calculateNthPrime(int i) {
		int nthPrime = i;
		int number = 1;
		int count = 0;
		int z;
			if (nthPrime <= 0) {
				throw new IllegalArgumentException();
			} else {
					while (count < nthPrime) {
						number = number + 1;
							for (z = 2; z <= number; z++) {
								if (number % z == 0) {
									break;
								}
							}
							if (z == number) {
								count = count + 1;
							}
					}
					return number;
			}
		}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String code = string.toLowerCase();
			char[] array = code.toCharArray(); //
			StringBuilder cipher = new StringBuilder();
			int forSpace = 0;
			for(char a: array) {
		        if (!Character.isLetterOrDigit(a)) //can remove replaceAll \\W+ if I use this for anything  not a digit/letter
	                continue;
	            if (Character.isLetter(a))
	                 cipher.append((char) (2 * 'a' + 25 - a)); //unicode standard running parallel a = 97 z=122
	            	//UTS #51 Unicode Emoji
	            else
	                cipher.append(String.valueOf(a));
	            if (++forSpace % 5 == 0) // i > 0 && i % 5 == 0 to space out the 5 characters
	                cipher.append(' ');
			}

	        if (forSpace % 5 == 0) //only allow 5 characters per cipher
	            cipher.delete(cipher.length() - 1, cipher.length() );
	        
	        return cipher.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			char[] array = string.toCharArray();
			StringBuilder cipher = new StringBuilder();
	        for (char a: array) {
	            if (Character.isLetter(a)) //same thing but backwards
	                cipher.append((char) (2 * 'a' + 25 - a)); // 2*97 +25 -122 = a
	            else if (Character.isDigit(a))
	                cipher.append((char) a); //leave it as such
	        }

			return cipher.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * A check digit is a number that is used to validate a series of numbers whose accuracy you want to insure.
	 *  Frequently the last digit of a number string such as identification number is a check digit. Lets say the 
	 *  identification number starts out at 6 digits. A calculation is done using the six digits and a seventh digit is 
	 *  produced as a result of the calculation. This number is the check digit. There are many calculations 
	 * that can be used - this example illustrates the logic of the MOD11 check digi
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 * 
	 * 
	 */
	public boolean isValidIsbn(String string) {
		 String remove = string.toLowerCase();
		 String clean = remove.replace("-", ""); //just remove the -
	        if (clean.length() != 10) { 
	        	return false;
	        }
	        char check = clean.charAt(clean.length() - 1);
	        
	        int length = 10;
	        int sum = 0; // has to be 10

	        for (int i = 0; i < clean.length()-1; i++, length--) {
	            char c = clean.charAt(i);
	            if (Character.isDefined(c)) {
	                sum += Character.getNumericValue(c) * length;
	            }
	        }
	        if (Character.isDigit(check)) {
	            sum += Character.getNumericValue(check);
	        } else if (check == 'x') { ///////don't forget!!
	            sum += 10; 
	        }

	        return sum % 11 == 0;
	    }


	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII!!!! letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 *take string scan it for each letter and empty string too
	 */
	public boolean isPangram(String string) {
		String clean = string.replaceAll("\\W+", "").toLowerCase();
		String[] array = clean.split("");
		HashSet<String> alpha = new HashSet<>();
		
		for(int i = 0; i < clean.length(); i++) {
			String check = array[i];
			
			if(check.matches("\\w+") && !alpha.contains(check)){
				alpha.add(array[i]);
			}
		}
		
		if (string.equals(" ")){
			return false;
		}
		
		return (alpha.size() == 26);
	
	}
	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * interface Framework-level interface defining read-write access to a temporal object,
	 *  such as a date, time, offset or some combination of these
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		LocalDateTime localDateTimeGiven = null;
		long giga = 1_000_000_000;
		
		try {
		 localDateTimeGiven = LocalDateTime.from(given);
		}
		catch(Exception e) {
		 localDateTimeGiven = ((LocalDate)given).atStartOfDay();
		}
		
		return localDateTimeGiven.plus(giga,ChronoUnit.SECONDS); 
	}
		

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * Plan of attack -- 
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		
	        int result = 0;
	        
	        for (int count = 1; count < i; count++) {
	            for (int multi : set) {
	                if (multi != 0 && count % multi == 0) {
	                    result += count;
	                    break;
	                }
	            }
	        }

	        return result;
	  
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * every even index * 2
	 * if ((even index * 2) > 9) { (even index * 2) - 0}
	 * sum1(sum(even index) + string)
	 * if (sum1 % 10 = 0
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		if (string.matches("^[a-zA-Z0-9_.-]*$")){
			String clean = string.replaceAll("\\D+", " ");
	        StringBuilder newString = new StringBuilder(clean);
	        System.out.println(newString);
			if (string.length() < 1) {
				return false;
			} else {
	        int valueToMulti;
	        int sum = 0;
	        int sum2 = 0;//to begin
	        for (int i = 1; i < newString.length(); i += 2) { //iterate over each even number
	        	valueToMulti = Character.getNumericValue(newString.charAt(i));
	            int product = valueToMulti * 2;
	            if (product > 9) {
	            	product -= 9;
	             }
	            sum += product;
	         }//complete step 1
	        //step 2
	        for (int i = 0; i < newString.length(); i += 2) {
	            valueToMulti = Character.getNumericValue(newString.charAt(i));
	            sum2 += valueToMulti;
	        	}
	        return (sum + sum2) % 10 == 0;
			}
	        
		}else {
				String clean = string.replaceAll("\\D+", "");
		        StringBuilder newString = new StringBuilder(clean);
		        System.out.println(newString);
				if (string.length() < 1) {
					return false;
				} else {
		        int valueToMulti;
		        int sum = 0;
		        int sum2 = 0;//to begin
		        for (int i = 1; i < newString.length(); i += 2) { //iterate over each even number
		        	valueToMulti = Character.getNumericValue(newString.charAt(i));
		            int product = valueToMulti * 2;
		            if (product > 9) {
		            	product -= 9;
		             }
		            sum += product;
		         }//complete step 1
		        //step 2
		        	for (int i = 0; i < newString.length(); i += 2) {
		        		valueToMulti = Character.getNumericValue(newString.charAt(i));
		        		sum2 += valueToMulti;
		        	}
		        return (sum + sum2) % 10 == 0;
					}
				}
			
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		//how to attack -- basically parse through plus = +, minus and multiplied, and divided
		//hypothetically, can use Char value of mi, mu, d, p, to determine
		String preCut = string.substring(0, string.length()-1);//get rid of ?
		String[] operate = preCut.split(" "); //split string up 
	      int result = Integer.parseInt(operate[2]); //lets get those numbers + or - and at 2nd index is always #
	      int i = 3; //index 3 is always operation
	      String check = "";
	      while (i < operate.length) { //operations at 3
	        if (check.length() == 0) {
	          check = operate[i];
	          if (check.equals("multiplied") || check.equals("divided")) { //
	            // at index 4 need to move the by 
	            i++;
	          }
	        } else {
	          // looking for a number
	          switch (check) {
	            case "plus":
	              result += Integer.parseInt(operate[i]);//wrap to int
	              break;
	            case "minus":
	              result -= Integer.parseInt(operate[i]);
	              break;
	            case "multiplied":
	              result *= Integer.parseInt(operate[i]);
	              break;
	            case "divided":
	              result /= Integer.parseInt(operate[i]);
	              break;
	            
	          }
	          check = ""; // for each subsequent interation
	        }
	        i++;
	      }
	      return result;
	 
	}

}

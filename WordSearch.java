import java.util.*;
import java.io.*;

public class WordSearch{
    private char[][]data;
	private int seed;
	private Random randgen;
	private ArrayList<String> wordsToAdd;
	private ArrayList<String> wordsAdded;
    /*public WordSearch(int rows,int cols){
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Indices cannot be negative");
		}
		data = new char[rows][cols];
		clear();
    }


    /*[rowIncrement,colIncrement] examples:
     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
     *[ 1,0] would add downwards because (row+1), with no col change
     *[ 0,-1] would add towards the left because (col - 1), with no row change
     */

	public WordSearch(int rows, int cols, String fileName) {
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Nonpositive integers are not allowed")
		}
		wordsToAdd = new ArrayList<String>();
		wordsAdded = new ArrayList<String>();
		data = new char[rows][cols];
		clear();
		randgen = new Random();
		try {
			File fil = new File(fileName);
			Scanner file = new Scanner(fil);
			while (file.hasNext()){
				String temp = file.next();
				wordsToAdd.add(temp.toUpperCase());
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error: File not found");
			e.printStackTrace();
		}
		addAllWords();
	}



	}

	public WordSearch( int rows, int cols, String fileName, int randSeed) {
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Nonpositive integers are not allowed")
		}
		wordsToAdd = new ArrayList<String>();
		wordsAdded = new ArrayList<String>();
		data = new char[rows][cols];
		clear();
		randgen = new Random(randSeed);
		try{
			File fil = new File(fileName);
			Scanner file = new Scanner(fil);
			while (file.hasNext()){
				String temp = file.next();
				wordsToAdd.add(temp.toUpperCase());
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error: File not found");
			e.printStackTrace();
		}
		addAllWords();
	}

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < data[i].length; x++) {
				data[i][x] = '_';
			}
		}
    }
    public String toString(){
		String ans = "";
		for (int i = 0; i < data.length; i++) {
			ans += "|";
			for (int x = 0; x < data[i].length; x++) {
				if (x == data[i].length - 1) {
					ans += data[i][x];
				}
				else {
					ans += data[i][x] + " ";
				}
			}
			ans += "|\n";
		}
		ans +=  "Words:";
		for (int i = 0; i < wordsAdded.size(); i++) {
			if (i != words.Added.size() - 1) {
				ans += wordsAdded.get(i) + ", ";
			}
			else {
				ans += words.Added.get(i);
			}
		return ans + " (seed: " + seed + ")";
    }



	private static char[][] copyWord(char[][] ary) {
		char[][] ans = new char[ary.length][ary[0].length];
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < data[i].length; x++) {
				ans[i][x] = ary[i][x];
			}
		}
		return ans;
	}


	 /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added in the direction rowIncrement,colIncrement
     *Words must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
     *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
     *@return true when: the word is added successfully.
     *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
     *        OR there are overlapping letters that do not match
     */
    private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
		char[][] ans = copyWord(data);
		if ((rowIncrement == 0 && colIncrement == 0) || (colIncremenet == 1 && data.length - row < word.length()) || (colIncremenet == -1 && row < word.length() - 1) || (rowIncremenet == 1 && data[row].length - col < word.length()) || (rowincremenet == -1 && col < word.length() - 1))  {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			if (! (data[row + rowIncremenet * i][col + colIncrement * i] == '_' || data[row + rowIncremenet * i][col + colIncrement * i] == word.charAt(i))) {
				return false;
			}
			temp[row + rowIncremenet * i][col + colIncrement * i] = word.charAt(i);
		}
		data = temp;
		return true;

    }

    private ArrayList<Integer> finder(boolean row) {

      if (row) {
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < data.length; i++) {
          int[i] = i;
        }
      }
      else {
        int[] ans = new int[data[0].length];
      }

      return ans;
    }

    private boolean addAllWords() {
      boolean success = true;
      String randWord;
      int randcoli;
      int randrowi;
      while (wordsToAdd.length > 0) {
        if (success) {
          randWord = addWord(wordsToAdd.get(random.nextInt(wordsToAdd.length + 1)));
          randcoli = random.nextInt(3) - 1;
          randrowi = random.nextInt(3) - 1;
          while (randcoli == 0 && randrowi == 0) {
            randrowi = random.nextInt(3) - 1;
            randcoli = random.nextInt(3) - 1;
          }
          success = false;
        }
        if (randWord, random.nextInt(data.length), random.nextInt(data[0].length, randrowi, randcoli) ) {
          wordsToAdd.remove(randWord);
          success = true;
        }
      }

    }




   /* public boolean addWordHorizontal(String word,int row, int col){
		if (row < 0 || col < 0 || row >= data.length || col+word.length() > data[row].length) {
			return false;
		}
		else if (word.length() > data[row].length - col + 1) {
			return false;
		}
		char[][] temp = copyWord(data);
			for (int x = col; x < col + word.length(); x++) {
				if (word.charAt(x - col) != data[row][x] && data[row][x] != '_'){
					return false;
				}
				temp[row][x] = word.charAt(x - col);
			}
		data = temp;
		return true;

    }
*/

    /*public boolean addWordVertical(String word,int row, int col){
		if (row < 0 || col < 0 || row+word.length() > data.length || col >= data[row].length) {
			return false;
		}
		else if (word.length() > data.length - row + 1) {
			return false;
		}
		char[][] temp = copyWord(data);
		for (int i = row; i < row + word.length(); i++) {
			if (word.charAt(i - row) != data[i][col] && data[i][col] != '_') {
				return false;
			}
			temp[i][col] = word.charAt(i - row);
		}
		data = temp;
		return true;
    }

	public boolean addWordDiagonal(String word, int row, int col) {
		if (row < 0 || col < 0 || row+word.length() > data.length || col+word.length() > data[row].length) {
			return false;
		}
		else if (word.length() > data.length - row + 1 || word.length() > data[row].length - col + 1) {
			return false;
		}
		char[][] temp = copyWord(data);
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != data[row][col] && data[row][col] != '_') {
				return false;
			}
			temp[row][col] = word.charAt(i);
			row++;
			col++;
		}
		data = temp;
		return true;
	}
}
*/

import java.util.*;
import java.io.*;

public class WordSearch{
    private char[][]data;
	private int seed;
	private Random randgen;
	private ArrayList<String> wordsToAdd = new ArrayList<String>();
	private ArrayList<String> wordsAdded = new ArrayList<String>();

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
			System.out.println("Rows and columns cannot be set as nonpositive integers.")
			System.exit(1);
		}
		data = new char[rows][cols];
		clear();
		seed = (int)(Math.random() * 10000);
		randgen = new Random(seed);
		try {
			File fil = new File(fileName);
			Scanner in = new Scanner(fil);
			while (in.hasNext()){
				wordsToAdd.add(in.next().toUpperCase());
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error: File " + fileName + " not found");
			e.printStackTrace();
			System.exit(1);
		}
		addAllWords();
		randNum();


	}


	public WordSearch( int rows, int cols, String fileName, int randSeed, String ans) {
		if (rows <= 0 || cols <= 0) {
			System.out.println("Rows and columns cannot be set as nonpositive integers.")
			System.exit(1);
		}
		data = new char[rows][cols];
		clear();
		seed = randSeed;
		if (seed > 10000 || seed < 0) {
			System.out.println("Seed must be between 0 and 10000");
			System.exit(1);
		}
		randgen = new Random(seed);
		try{
			File fil = new File(fileName);
			Scanner in = new Scanner(fil);
			while (in.hasNext()){
				wordsToAdd.add(in.next().toUpperCase());
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error: File " + fileName + " not found");
			e.printStackTrace();
			System.exit(1);
		}
		addAllWords();
		if (! ans.toLowerCase().equals("key")) {
			randNum();
		}
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
			if (i != wordsAdded.size() - 1) {
        ans += wordsAdded.get(i) + ", ";
			}
			else {
				ans += wordsAdded.get(i);
			}
		}
		return ans + " (seed: " + seed + ")";
    }



	private char[][] copyWord(char[][] ary) {
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
		try {
			for (int i = 0; i < word.length(); i++) {
				if (ans[row][col] != word.charAt(i) && ans[row][col] != '_') return false;
				ans[row][col] = word.charAt(i);
				row += rowIncrement;
				col += colIncrement;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		data = ans;
		return true;
    }

   /*private ArrayList<Integer> finder(boolean row) {
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
	*/

	 private boolean allOne(int[] ary) {
		for (int i = 0; i < ary.length; i++) {
			if (ary[i] == 0) {
				return false;
			}
		}
		return true;
	}
	

	private boolean allOne(int[][] ary) {
		for (int i = 0; i < ary[0].length; i++) {
			if (! allOne(ary[i])) {
				return false;
			}
		}
		return true;
	}
	


    private boolean addAllWords() {
		int c = 0; int word; int row; int col; int rowI; int colI; boolean succ = false;
		while (wordsToAdd.size() > 0) {
			word = Math.abs(randgen.nextInt() % wordsToAdd.size());
			succ = false;
			while (!succ && c < 150) {
				rowI = Math.abs(randgen.nextInt() % 3) - 1;
				colI = Math.abs(randgen.nextInt() % 3) - 1;
				while ((colI == 0 && rowI == 0) && c < 150) {
					c += 1;
					rowI = Math.abs(randgen.nextInt() % 3) - 1;
					colI = Math.abs(randgen.nextInt() % 3) - 1;
				}
				row = Math.abs(randgen.nextInt() % data.length);
				col = Math.abs(randgen.nextInt() % data[0].length);
				if (addWord(wordsToAdd.get(word), row, col, rowI, colI)) {
					wordsAdded.add(wordsToAdd.remove(word));
					c = 0;
					succ = true;
				}
				c += 1;
			}
			if (! succ) {
				wordsToAdd.remove(word);
				c = 0;
			}
		}
		return true;
	}

	private void randNum() {
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < data[0].length; x++) {
				if (data[i][x] == '_') {
					data[i][x] = (char)(Math.abs(randgen.nextInt() % 26) + 'A');
				}
			}
		}
	}

	private static void instructions() {
		System.out.println("Please run the program with 3 to 5 valid inputs (number_of_rows, number_of_columns, file_name, seed (optional), key (optional; only given when seed is also given))");
		System.exit(1);
	}

	public static void main(String[] args) {
		try {
			if (args.length == 3) {
				if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0) {
					instructions();
				}
				WordSearch ans = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
				System.out.println(ans);
			}
			else if (args.length == 4) {
				if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0) {
					instructions();
				}
				WordSearch ans = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), "");
				System.out.println(ans);
			}
			else if (args.length == 5) {
				if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0) {
					instructions();
				}
				WordSearch ans = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), args[4]);
				System.out.println(ans);
			}
		} catch (NumberFormatException e) {
			instructions();
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
	*/
}

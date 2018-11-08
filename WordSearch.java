public class WordSearch{
    private char[][]data;

    /**Initialize the grid to the size specified 
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Indices cannot be negative");
		}
		data = new char[rows][cols];
		clear();
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < data[i].length; x++) {
				data[i][x] = '_';
			}
		}
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
		String ans = "";
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < data[i].length; x++) {
				if (x == data[i].length - 1) {
					ans += data[i][x];
				}
				else {
					ans += data[i][x] + " ";
				}
			}
			ans += "\n";
		}	
		return ans;
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
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned 
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
		if (row < 0 || col < 0 || row >= data.length || col >= data[row].length) {
			return false;
		}
		else if (word.length() > data[row].length - col + 1) {
			return false;
		}
		char[][] temp = copyWord(data);
		for (int i = 0; i < data.length; i++) {
			if (i == row) {
				for (int x = col; x < word.length(); x++) {
					if (word.charAt(x - col) != data[i][x] && data[i][x] != '_'){
						return false;
					}
					temp[i][x] = word.charAt(x- col);
				}
			}
		}
		data = temp;
		return true;
		
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
		if (row < 0 || col < 0 || row >= data.length || col >= data[row].length) {
			return false;
		}
		else if (word.length() > data.length - row + 1) {
			return false;
		}
		char[][] temp = copyWord(data);
		for (int i = row; i < word.length(); i++) {
			if (word.charAt(i - row) != data[i][col] && data[i][col] != '_') {
				return false;
			}
			temp[i][col] = word.charAt(i - row);
		}
		data = temp;
		return true;
    }
}

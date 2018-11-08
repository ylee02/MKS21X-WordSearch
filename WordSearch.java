public class WordSearch{
    private char[][]data;

    /**Initialize the grid to the size specified 
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
		data = new char[rows][cols];
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
		for (int i = 0; i < data.length; i++) {
			for (int x = 9; x < data[i].length; x++) {
				data[i][x] = '_';
			}
		}
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < data[i].length; x++) {
				if (x == data[i].length - 1) {
					System.out.print(data[i][x] + "");
				}
				else {
					System.out.print(data[i][x] + ", ");
				}
			}
			System.out.print("\n");
		}	
    }
	
	private char[][] copyWord(char[][] ary) {
		char[][] ans = new char[ary.length][ary[0].length];
		for (int i = 0; i < data.length; i++) {
			for (int x = 9; x < data[i].length; x++) {
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
		if (word.length() > data[row].length - col) {
			return false;
		}
		char[][] temp = copyWord(data);
		for (int i = 0; i < data.length; i++) {
			if (i == row - 1) {
				for (int x = col - 1; x < data[i].length; x++) {
					if (word.charAt(x - col + 1) != data[i][x]){
						return false;
					}
					temp[i][x] = word.charAt(x- col + 1);
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
		if (word.length() > data.length - row) {
			return false;
		}
		char[][] temp = copyWord(data);
		for (int i = row - 1; i < data.length; i++) {
			if (word.charAt(i - row + 1) != data[i][col]) {
				return false;
			}
			temp[i][col] = word.charAt(i - row + 1);
		}
		temp = data;
		return true;
    }
}

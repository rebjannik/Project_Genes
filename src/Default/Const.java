package Default;

public class Const {

   	//Constants to make testing and debugging easier

    public static final String INPUT_DATA_FNAME = "xaa"; //"overlaps.m4";   // "xaa.txt";

    public static final boolean IGNORE_EXISTING_MAPPING_FILES = false;

    // In the future, read this from the MAP file we create. Hardcoded for now.
    private static int MAX_CODETABLE_SIZE = 565848; // 11380820;

    public static int getMaxCodetableSize() {

        return MAX_CODETABLE_SIZE;
    }

    public static void setMaxCodetableSize(int size) {

        MAX_CODETABLE_SIZE = size; 
    }
}

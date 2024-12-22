package model;

import java.util.Arrays;

// Wrote input parser class to identify noun and verb in user input at a particular game level
public class InputParser {
    int level; //gameLevel
    Model obj;
    String[] verbs;
    String[] nouns;

    public InputParser(Model model) {
        this.obj = model;
    } //set model object

    public void setLevel(int level) {
        this.level = level;
    } //set game level

    public void setVerbs() {
        verbs = obj.getVerbs(level);
    } //set game verbs

    public void setNouns() {
        nouns = obj.getNouns(level);
    } //set game noun

    //Actual method to parse Input and fetch noun & verb
    public String[] parseInput(String input) {
        String verb = "Not Found", noun = "Not Found";
        int flag_noun = 0;
        int flag_verb = 0;
        String[] words = input.split(" "); //divide input in words for better recognition
        for (String word : words) {
            if (flag_noun == 1 && flag_verb==1) { //both identified, hence no need to process further
                break;
            }
            if (Arrays.asList(verbs).contains(word)) { //search for verb
                verb = word;
                flag_verb += 1;
            } else if (Arrays.asList(nouns).contains(word)) { //search for noun
                noun = word;
                flag_noun += 1;
            }
        }
        return new String[]{noun, verb};
    }
}

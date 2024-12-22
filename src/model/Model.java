package model;

import java.util.Arrays;
import java.util.Map;

/*
Model class represents the game state all associated game attributes
It is responsible for outputting all game related information like nouns, verbs to controller.
It is also responsible for checking noun-verb combination and giving output
*/
public class Model {
    // Defining class atttributes
    String[] commands;
    Map<String, String> surroundings;
    String[][] verbs;
    String[][] nouns;
    String[] introductionScenario;
    String[] hints;
    Map[] nounToVerbAssociation;
    Map[] verbToNounAssociation;
    Map<String, String[][]> nounVerbWithLevels;
    String[][] sublevelHints;
    int sublevel;
    int totalLevels;

    public Model() {
        //instantiating class attributes using constants class
        this.commands = Constants.commands;
        this.nouns = Constants.nouns;
        this.verbs = Constants.verbs;
        this.nounToVerbAssociation = Constants.nounToVerbAssociation;
        this.verbToNounAssociation = Constants.verbToNounAssociation;
        this.nounVerbWithLevels = Constants.nounVerbWithLevels;
        this.sublevelHints = Constants.sublevelhints;
        this.hints = Constants.hints;
        this.introductionScenario = Constants.introductionScenario;
        this.sublevel = 0;
        this.totalLevels= Constants.introductionScenario.length;
    }

    public String[] getCommands() {
        return this.commands;
    }

    public String getHint(int level) {
        return this.hints[level];
    }

    public int getTotalLevels() {return this.totalLevels;}

    public void setSublevel(int sublevel) {
        this.sublevel = sublevel;
    }

    public int getSublevel() {
        return this.sublevel;
    }

    public String[] getVerbs(int level) {
        return this.verbs[level];
    }

    public String[] getNouns(int level) {
        return this.nouns[level];
    }

    public String getIntroductionScenario(int level) {
        return this.introductionScenario[level];
    }

    //This method is responsible to check if noun and verb combination provided are correct for the game level
    public boolean checkNounVerbCombination(String[] detectedNounandVerb, int level) {
        String noun = detectedNounandVerb[0];
        String verb = detectedNounandVerb[1];
        //this.nounVerbWithLevels.get(noun)[level] gives us verbs associated with noun provided for the level
        if (Arrays.stream(this.nounVerbWithLevels.get(noun)[level]).toList().contains(verb)) {
            return true;
        }
        return false;
    }

    //Following method generates required and correct outputs for user and passes them to controller
    public String[] getOutput(String[] detectedNounandVerb, int level) {
        if (Arrays.asList(detectedNounandVerb).contains("Not Found")) {
            //if both noun & verb are not found, then we just give hints on different sublevels,
            //for current level
            if (detectedNounandVerb[0] == "Not Found" && detectedNounandVerb[1] == "Not Found") {
                return new String[]{"Unable to find appropriate noun and verb in your sentence.\n" +
                        "Please try re-phrasing your sentence :(\n" +
                        "Hint : " + this.hints[level]}
                        ;
            }
            //if only noun is not found, we hint towards specific verbs/actions related to the noun
            else if (detectedNounandVerb[0] == "Not Found") {
                return new String[]{"Unable to find appropriate noun in your sentence.\n" +
                        "Probable correct action you might take : \n" +
                        this.verbToNounAssociation[level].get(detectedNounandVerb[1])};
            }
            //if only verb is not found, we hint to specific nouns related to the action (verb)
            else if (detectedNounandVerb[1] == "Not Found") {
                return new String[]{"Unable to find appropriate action for your sentence.\n" +
                        "Probable correct action you might take : \n" +
                        this.nounToVerbAssociation[level].get(detectedNounandVerb[0])};
            }
        }
        else {
            //if noun and verb both are found, we check their combination
            if (this.checkNounVerbCombination(detectedNounandVerb, level)) {
                //if combination is valid, we further check if the noun-verb
                //combination used is suitable for current sublevel
                if (this.nouns[level][this.sublevel].equals(detectedNounandVerb[0])) {
                    //if it is, increase the sublevel
                    this.setSublevel(this.sublevel + 1);
                    //if all sublevels are completed for level, we indicate increase in game level by passing "1"
                    if (this.getSublevel() == this.nouns[level].length) {
                        return new String[]{"Nice, You advanced to next level, well done :)", "1"};
                    } else {
                        //else we indicate user to complete next sublevel using sublevel hints in constants class
                        return new String[]{"Hurry, move on to next step - \n" + this.sublevelHints[level][this.sublevel]};
                    }
                }
                // if verb noun combination doesn't match for current sublevel, we just hint what is current task
                else {
                    return new String[]{this.sublevelHints[level][this.sublevel]};
                }

            } else {
                // if noun and verb combination fails we hint on associate verbs/actions for noun
                //and vice versa for current level and return "0" as part of return statement to indicate
                //sublevel is not passed
                return new String[]{"Noun and verb seems to not match,\n" +
                        "Probable correct action you might take : \n" +
                        this.verbToNounAssociation[level].get(detectedNounandVerb[1]) +
                        "  " +
                        this.nounToVerbAssociation[level].get(detectedNounandVerb[0]), "0"};
            }
        }
        return new String[]{};
    }
};


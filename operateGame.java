import javax.swing.*;

public class operateGame {

    private int countCorrectGuess = 0, countIncorrectGuess = 0, guessNum, finalNum, randomNum, roundsPlayed = 0, attempt = 0, roundAttempt = 0, currentIncorrect = 0;
    private boolean check = false;
    private String strGuess;

    public int getRandomNum(){
        randomNum = 1 + (int)(100*Math.random());
        return randomNum;
    }

    public int setgetFinalNum(){
        getRandomNum();
        this.finalNum = randomNum;
        return finalNum;
    }
    
    public void runHint(int guessed, int numToGuess){
        if (guessed > numToGuess){
            attempt++; roundAttempt++; countIncorrectGuess++; currentIncorrect++;
            JOptionPane.showMessageDialog(null, "Your guess "+guessed+" is Greater than the number."+"\nTry to GUESS LOWER!", "Incorrect Guess", JOptionPane.WARNING_MESSAGE);
            //guessNum = 0;
        }
        else if (guessed < numToGuess){
            attempt++; roundAttempt++; countIncorrectGuess++; currentIncorrect++;
            JOptionPane.showMessageDialog(null, "Your guess "+guessed+" is Less than the number."+"\nTry to GUESS HIGHER!", "Incorrect Guess", JOptionPane.WARNING_MESSAGE);
            //guessNum = 0;
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Input. Attempt does not count!", "Incorrect Guess", JOptionPane.WARNING_MESSAGE);
            //guessNum = 0;
        }
    }

    public void winConstruct(int guessed, int numToGuess){
        JOptionPane.showMessageDialog(null,
        "Your guess was CORRECT: "+guessed+
        "!! \n\nNicely done, Einstein! You're a genius!\n\n"+
        "Below is the summary of your activity:"+
        "\n- Round(s) Played: "+roundsPlayed+
        "\n- Correct Guesses: "+countCorrectGuess+
        "\n\n- Current Round Incorrect Guesses: "+currentIncorrect+
        "\n- Total Incorrect Guesses: "+countIncorrectGuess+
        "\n\n- Current Round Attempt(s): "+roundAttempt+
        "\n- Total Runtime Attempt(s): "+attempt+
        "\n- The Generated Number: "+numToGuess+"\n\n");
    }

    public void getByeMessage(){
        JOptionPane.showMessageDialog(null, "Goodbye. Thanks for playing!", "Exit", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }    

    public void startGame(){
    roundsPlayed++;
        setgetFinalNum();

    while(!check){
            guessNum = 0;
            strGuess = JOptionPane.showInputDialog(null, "Time to guess the number!\n"+"Type your guess below\n\n"+"I'm guessing number...", "Guessing Game", 1);

        try {
            guessNum = Integer.valueOf(strGuess);
            if ((guessNum > 100) || (guessNum < 1)){
                int b = JOptionPane.showOptionDialog(null, "Error Found: '"+ guessNum +"' not in range (1-100)."+" \n\nAttempt does not count!"+ " Try again?\n\n", "Input Out of Range", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                if((b == 1) || (b == -1)){
                    getByeMessage();
                } 
            }
        }
        catch (NumberFormatException nfe){
            if(strGuess == null){
                getByeMessage();
            }

            int b = JOptionPane.showOptionDialog(null, "Error Found: "+ nfe.getMessage()+" not a valid input."+"\n\nAttempt does not count!"+" Try again?\n\n", "Input Invalid", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                if((b == 1) || (b == -1)){
                    getByeMessage();
                }
        }

        if ((guessNum != 0) && (guessNum < 101)){
            if(guessNum > 0){
                if (finalNum == guessNum){
                    attempt++; countCorrectGuess++; roundAttempt++;
                    winConstruct(guessNum, finalNum);
                    check = true;
                }
                else{
                    runHint(guessNum, finalNum);
                }
            }
        }
        }
        startGameAgain(guessNum, roundAttempt);   
    }

    public void startGameAgain(int guessed, int takes){
        Object[] Option3 = {"Play again", "No, thanks"};

        int a = JOptionPane.showOptionDialog(null, "Wow! you guessed "+guessed+" in "+takes+" tries."+"\nWould you like to try your luck again?", "Play Again?\n\n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, Option3, Option3[1]);

            if(a == JOptionPane.YES_OPTION){
                currentIncorrect = 0;
                roundAttempt = 0;
                guessNum = 0;
                check = false;
                startGame();
            }
            else if (a == JOptionPane.NO_OPTION){
                getByeMessage();
            }
            else{
                System.exit(0);
            }
    }

    public void exitGame(){
        Object[] options2 = {"Yes", "No, I want to play"};
        int exit = JOptionPane.showOptionDialog(null, "Are you sure you don't want to play this game?", "Exit Guessing Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
            if (exit == 0){
                JOptionPane.showMessageDialog(null, "Goodbye...", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            else if(exit == -1){
                System.exit(0);
            }
        launchGame();
    }

    public void launchGame(){

        Object[] options1 = {"Yes, I want to play", "No, my luck is terrible"};

        int select = JOptionPane.showOptionDialog(null,"Would you like to play a Guessing Game?\n"+"Guess the random number from 1 to 100.\n"+"It's that easy! Game?\n\n", "A Guessing Game", JOptionPane.YES_NO_OPTION, 3, null, options1, options1[0]);

            switch (select) {
                case 0: startGame();
                    break;

                case 1: exitGame();
                    break;

                case -1: System.exit(0);
                    break;

                default: JOptionPane.showMessageDialog(null, "Unexpected Error", "Exit Program", JOptionPane.ERROR_MESSAGE); System.exit(0);
                    break;
            }
    }
}

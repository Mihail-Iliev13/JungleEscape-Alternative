package junglesurvival;

import junglesurvival.participants.Gender;

import java.util.Random;

public class User {

  private String nickName;
  private Gender gender;
  private int age;
  private static final int MIN_REQUIRED_AGE_TO_PLAY_THE_GAME = 18;

  public User(String nickName, Gender gender, int age){
    setNickName(nickName);
    setGender(gender);
    setAge(age);
  }

  public void setNickName(String nickName) {

    if(nickName == null || nickName.equals("")){
      System.out.println("Invalid name");
    } else {
      this.nickName = nickName;
    }
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public void setAge(int age) {
    if(age < MIN_REQUIRED_AGE_TO_PLAY_THE_GAME){
      GameMessages.showUserUnderAgeMessage();
      System.exit(0);
    } else {
      this.age = age;
    }
  }

  public int throwDice(){
    Random rand = new Random();
    int diceValue = rand.nextInt(6) + 1;
    GameMessages.showDiceValue(diceValue);
    return diceValue;
  }
}

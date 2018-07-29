package junglesurvival;

import junglesurvival.items.jewels.Jewel;
import junglesurvival.items.jewels.JewelColor;
import junglesurvival.items.mushrooms.Mushroom;
import junglesurvival.items.potions.Potion;
import junglesurvival.items.potions.PotionType;
import junglesurvival.items.weapons.Weapon;
import junglesurvival.items.weapons.WeaponType;
import junglesurvival.participants.*;
import junglesurvival.participants.interfaces.Flyable;

import java.util.ArrayList;


public class GameMessages {
  private GameMessages(){

  }
  public static void showWinMessage(int jewels){

    System.out.printf("CONGRATULATIONS! YOU ESCAPED THE JUNGLE ALIVE AND COLLECTED %d JEWEL%s\n"
            ,jewels, GameMessages.pluralOrNot(jewels).toUpperCase());
  }

  public static void showDeadMessage(){
    System.out.println("YOU DIED! THE GAME IS OVER.");
  }

  public static void showJewelMessage(Jewel jewel){
    System.out.printf("There is a %s jewel in this cell!\n", jewel.getColor().toString().toLowerCase());
  }

  public static void showPotionMessage(Hero hero, Potion potion){
    String messageBeginning = "There is potion in this cell. It can increase your";
    if (potion.getType().equals(PotionType.EXPERIENCE)) {
      System.out.printf("%s experience! You better drink it right away!\n", messageBeginning);
    } else {

      if (hero.getLifepoints() == Hero.MAX_LIFE_POINTS) {
        System.out.printf("%s health! %s is with maximum health right now.\n" +
                        "If you drink the potion now %s's lifepoints won't be increased.\nYou better save it for later.Drink it or save it?\n"
                      , messageBeginning, hero.getName(), hero.getName());
      } else if (hero.getLifepoints() + potion.getPointsGiven() > Hero.MAX_LIFE_POINTS) {
        System.out.printf("%s health! If %s drinks the potion now %s lifePoints will increase" +
                ", but potion won't be used in most efficient way\n" +
                "because lifepoints that the potion gives, added to %s's" +
                " lifepoints are bigger than your hero maximum lifepoints.\nYou better save it for later.Drink it or save it?\n"
                , messageBeginning, hero.getName(), GameMessages.hisOrHers(hero), hero.getName());

      } else {
        System.out.printf("%s health! %s's lifepoints are %d. Drink it or save it?\n",
                messageBeginning, hero.getName(), hero.getLifepoints());
      }

    }
  }

  public static void showMushroomMessage(){
    System.out.println("There is a mushroom in this cell. Mushroom can be either good or bad.\n" +
            "Good mushroom will increase your health but a bad one will decrease it.\nDo you want to eat it? Yes or no?");
  }

  public static void showGoodMushroomEatenMessage(Hero hero, Mushroom mushroom){
    System.out.printf("This was a mordant mushroom! Your life points were increased with %d points." +
            "\nLIFE POINTS: %d\n", mushroom.getMushroomPoints(), hero.getLifepoints() );
  }

  public static void showBadMushroomEatenMessage(Hero hero, Mushroom mushroom){
    System.out.printf("Oh no, this mushroom was poisonous! Your life points were reduced with %d points." +
            "\nLIFE POINTS: %d\n", mushroom.getMushroomPoints(), hero.getLifepoints() );
  }

  public static void showLevelUpMessage(Hero hero){
    System.out.printf("You leveled up!\nLEVEL: %d.\nDAMAGE: %d.\n",
            hero.getLevel(), hero.getDamage());
  }

  public static void showPickItemMessage(Hero hero, String item){
    System.out.printf("%s picked up this %s. Now it's in %s bag.\n"
            , hero.getName(), item, GameMessages.hisOrHers(hero));
  }

  public static void showBribedEnemyMessage(Enemy enemy){
    System.out.printf("You have successfully bribed %s. Now you can move on!\n", enemy.getName());
  }

  public static void showEnemyReducedLifePointsMessage(Enemy enemy, Hero hero){
    System.out.printf("%s's life points were reduced with %d points.\n%s LIFE POINTS: %d\n"
            , enemy.getName(), hero.getDamage(), enemy.getName().toUpperCase(), enemy.getLifepoints());
  }

  public static void showHeroLifePointsReducedMessage(Hero hero, Enemy enemy){
    System.out.printf("%s's life points were reduced with %d point%s.\n%s LIFE POINTS: %d\n"
            , hero.getName(), enemy.getDamage(), GameMessages.pluralOrNot(enemy.getDamage())
            ,hero.getName().toUpperCase(), hero.getLifepoints());
  }

  public static void showEnemyCantBeBribedMessage(Enemy enemy){
    System.out.printf("You don't posses the needed jewel to bribe %s. The only option you have is to fight him.\n",
            enemy.getName());
  }

  public static void showMissingWeaponMessage(WeaponType weapon){
    System.out.printf("You don't have %s in your bag.\n", weapon.toString());
  }

  public static void showMissingWeaponMessage(String weapon){
    System.out.printf("You don't have %s in your bag.\n", weapon);
  }

  public static void showWeaponMessage(Weapon weapon){
    System.out.printf("There's %s in this cell. " +
            "Do you want to make it your current weapon or put it in your bag? " +
            "Equip or pick?\n", weapon.getType().toString().toLowerCase());
  }

  public static void showEquipWeaponMessage(Hero hero, WeaponType oldWeapon, WeaponType newWeapon){
    System.out.printf("%s's %s has been put to the bag.\nYour hero is now equipped with %s and %s damage has changed to %d points.\n"
            ,hero.getName(), oldWeapon, newWeapon, GameMessages.hisOrHers(hero), hero.getDamage() );
  }

  public static void showAnimalMessageForHunter(Animal animal){
    System.out.printf("There is %s in this cell. You can either fight it, tame it or skip it." +
            " What's your choice?\n", animal.getName());
  }

  public static void showAnimalMessageForAmazon(Animal animal){
    System.out.printf("There is an %s in this cell. You can either fight it or skip it." +
            " What's your choice?\n", animal.getName());
  }

  public static void showBribableEnemyMessage(Enemy monster){
    System.out.printf("There is %s in this cell. You can either fight him or bribe him. Fight or bribe?\n"
            , monster.getName());
  }

  public static void showGainExperienceMessage(Hero hero, Enemy enemy){
    System.out.printf("This %s is dead! %d point%s were added to your experience points!\nEXPERIENCE POINTS: %d\n"
            , enemy.getName(), enemy.getExperiencePointsGiven()
            , GameMessages.pluralOrNot(enemy.getExperiencePointsGiven()), hero.getExperiencePoints());
  }

  public static void showGainExperienceMessage(Hero hero, Potion potion){
    System.out.printf("You drank an experience potion. %d experience points were added to your experience!\nEXPERIENCE POINTS: %d\n"
            , potion.getPointsGiven(), hero.getExperiencePoints());
  }

  public static void showHealMessage(Hero hero, int potionHealthPoints){
    System.out.printf("%s drank a health potion! Your life points were increased with %d points.\nLIFE POINTS: %d\n"
            ,hero.getName(), potionHealthPoints, hero.getLifepoints());
  }

  public static void showEmptyCellMessage(){
    System.out.println("This cell is empty. Move to next one");
  }

  public static void showNewUserMessage(){
    System.out.println("Before the game begins, please on the next row enter your nickname, gender(\"male\" or \"fenale\") and your age!\n" +
            "You can only use numbers and alphabetical letters for your nickname. No spacing is allowed!");
  }

  public static void showChooseHeroMessage(){
    System.out.println("You have to choose your hero now. You can either choose Indian Hunter, or Amazon Warrior.\n" +
            "Type down \"hunter\" for Indian Hunter or \"amazon\" for Amazon Warrior.");
  }

  public static void showGameStartMessage(Hero hero){
    if(hero instanceof IndianHunter){
      System.out.printf("You've chosen Indian Hunter to be your hero. His current weapon is %s.\n" +
              "You are ready to start.\n", hero.getCurrentWeapon());
    }
  }

  public static void showThrowDiceMessage(){
    System.out.println("Throw the dice!");
  }

  public static void showDiceValue(int diceValue){
    System.out.printf("Dice value is %d.\n", diceValue);
  }

  private static String hisOrHers(Hero hero) {
    if(hero.getGender().equals(Gender.MALE)) {
      return "his";
    }
    return "hers";
  }

  private static String pluralOrNot(int number){
    if(number == 1){
      return "s";
    }
    return "";
  }

  public static void showAnimalTamedMessage(IndianHunter indianHunter, Animal animal) {
    System.out.printf("Now %s is the master of this %s.\n", indianHunter.getName(), animal.getName());
  }

  public static void showUntameableMessage(JewelColor blue, Animal animal) {
    System.out.printf("You dont't posses %s jewel so you can't tame this %s.\n",
            blue.toString().toLowerCase(), animal.getName());
  }

  public static void showIdenticalAnimalsMessage() {
    System.out.println("Identical animals can't attack each other.\nYou have to attack this animal by yourself.");
  }

  public static void showTamedAnimalReducedLifePoints(Animal animal) {
    System.out.printf("%s's life points were reduced to %d.\n", animal.getName(), animal.getLifepoints());
  }

  public static void attackWithTamedAnimal(Hero hero, Animal tamedAnimal) {
    System.out.printf("%s attacked with his %s.\n", hero.getName(), tamedAnimal.getName());
  }

  public static void showMagicEyesMessage(AmazonWarrior amazon, Enemy enemy) {
    System.out.printf("%s used her magic eyes to hypnotize this %s and made two consecutive attacks,\n"
            ,amazon.getName(), enemy.getName());
  }

  public static void showSwitchWeaponMessage(Hero hero){
    StringBuilder availableWeapons = new StringBuilder();
    ArrayList<Weapon> weapons = hero.listOfWeapons();
    if(weapons.size() < 1){
      System.out.printf("Your %s doesn't have any weapons available in his bag." +
              "\nYour only choice is to fight with your current weapon\nType down \"current\" to proceed.\n", hero.getName());
      return;
    }
    for (Weapon weapon : weapons) {
      availableWeapons.append(weapon.toString()).append(", ");
    }
    String heroWeapons = availableWeapons.substring(0, availableWeapons.length() - 2);
    String heroName = hero.getName();

    System.out.printf("%s is about to fight! You can change %s weapon before battle.\nHere are the weapons in %s bag: %s." +
            "\nChoose one by typing its name or type \"current\" if you don't want to change your weapon.\n"
            ,heroName, GameMessages.hisOrHers(hero), GameMessages.hisOrHers(hero),heroWeapons);

  }

  public static void showWeaponAlreadyPickedUpMessage(Hero hero, Weapon newWeapon) {
    System.out.printf("There is %s in this cell. %s already has %s. Move on!\n"
            ,newWeapon.getType().toString(), hero.getName(), newWeapon.getType().toString());
  }

  public static void showDrinkPotionMessage(Hero hero) {
    System.out.printf("%s has health potion in his bag.\nDrink it or not? Type \"yes\" or \"no\".\n", hero.getName());
  }

  public static void showUserUnderAgeMessage(){
    System.out.println("You must be at least 18 years old to play this game!\nGo play with your baby dolls!");
  }

  public static void showAttackMessage(Participant attacker) {
    System.out.printf("%s attacks with %d damage points!\n", attacker.getName(), attacker.getDamage());
  }

  public static void showThrowAgainMessage() {

    System.out.println("Dice value is beyond board's boundaries. Throw again!");
  }

  public static void showCreatureFlyAwayFromHeroMessage(Enemy creature){

    System.out.printf("%s: Haha, your weapon doesn't have the same effect on me when I'm up here in the sky!\n"
            , creature.getName().toUpperCase());

  }

  public static void showFierceAttackMessage(Monster monster) {
    System.out.printf("Beware!%s attacks with fierce and its damage points are %d.\n"
            ,monster.getName(), monster.getDamage());
  }
}

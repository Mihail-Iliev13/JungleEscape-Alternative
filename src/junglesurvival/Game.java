package junglesurvival;

import junglesurvival.items.jewels.Jewel;
import junglesurvival.items.mushrooms.Mushroom;
import junglesurvival.items.potions.Potion;
import junglesurvival.items.weapons.Weapon;
import junglesurvival.participants.*;
import junglesurvival.participants.interfaces.Fieldable;

import java.util.Scanner;

public class Game {

  private Scanner in = new Scanner(System.in);
  private static final int DICE_MAX_VALUE = 6;
  private Hero hero;

  public void start() {
   play();
  }

  private void play() {

    GameMessages.showNewUserMessage();
    String[] userInput = in.nextLine().split(" ");
    Gender gender;

    if (userInput[1].equals("male")) {
      gender = Gender.MALE;
    } else {
      gender = Gender.FEMALE;
    }

    User user = new User(userInput[0], gender, Integer.parseInt(userInput[2]));
    GameMessages.showChooseHeroMessage();
    String userInput2 = in.nextLine();

    if (userInput2.equals("hunter")) {
      IndianHunter hunter = new IndianHunter();
      GameMessages.showGameStartMessage(hunter);
      moveHero(hunter, Field.generateNewField(), 0, 0, user);
    } else {
      AmazonWarrior amazon = new AmazonWarrior();
      GameMessages.showGameStartMessage(amazon);
      moveHero(amazon, Field.generateNewField(), 0, 0, user);
    }
  }

  private void moveHero(Hero hero, Fieldable[][] field, int currRow, int currCol, User user) {

    int diceValue;
    String typeThrow = "";

    while (!typeThrow.equals("throw")){
      GameMessages.showThrowDiceMessage();
        typeThrow = in.nextLine();
    }

    if (currRow == field.length - 1
            && field[currRow].length - currCol < DICE_MAX_VALUE) {

      diceValue = user.throwDice();
      int upperBound = (field[currRow].length - 1) - currCol;
      while (diceValue > upperBound) {
        typeThrow = in.nextLine();
        diceValue = user.throwDice();
      }
    } else {
      diceValue = user.throwDice();
    }

    int[] newRowAndCol = nextPosition(field,currRow, currCol, diceValue);
    currRow = newRowAndCol[0];
    currCol = newRowAndCol[1];

    if (currRow == field.length - 1 && currCol == field[currRow].length - 1) {
      GameMessages.showWinMessage(hero.jewelsCollected());
      System.exit(0);
    }

    Fieldable current = field[currRow][currCol];

    if (current == null) {
      GameMessages.showEmptyCellMessage();
      moveHero(hero, field, currRow, currCol, user);

    } else if (current instanceof Jewel) {

      Jewel jewel = (Jewel) current;
      GameMessages.showJewelMessage(jewel);
      hero.pickItem(jewel);

    } else if (current instanceof Potion) {

        Potion potion = (Potion)current;
        GameMessages.showPotionMessage(hero, potion);
        String userInput = in.nextLine();

        switch (userInput) {
          case "drink":
            hero.drink(potion);
            break;
          case "save":
            hero.pickItem(potion);
            break;
            default:
              break;
        }

    } else if (current instanceof Mushroom) {
          Mushroom mushroom = (Mushroom)current;
          GameMessages.showMushroomMessage();
          String userInput = in.nextLine();
          if (userInput.equals("Yes") || userInput.equals("yes")) {
            hero.eat(mushroom);
          }
    } else if (current instanceof Weapon) {
      Weapon newWeapon = (Weapon) current;

      if (hero.hasWeapon(newWeapon) || hero.getCurrentWeapon().equals(newWeapon)) {
        GameMessages.showWeaponAlreadyPickedUpMessage(hero, newWeapon);
      } else {
      GameMessages.showWeaponMessage(newWeapon);
        String userInput = in.nextLine();
        if (userInput.equals("equip")) {
          hero.equip(newWeapon);
        } else if (userInput.equals("pick")) {
          hero.pickItem(newWeapon);
        }
      }

    } else if (current instanceof Animal) {

        Animal animal = (Animal)current;
        if (hero instanceof IndianHunter) {
          IndianHunter hunter = (IndianHunter) hero;
          GameMessages.showAnimalMessageForHunter(animal);
          String userInput = in.nextLine();

          if (userInput.equals("tame")) {
            hunter.tameAnimal(animal);
            if (hunter.getTamedAnimal() == null) {
              System.out.println("Fight or skip?");
              userInput = in.nextLine();
              switch (userInput) {
                case "fight":
                  hero.fight(animal);
                  break;
                default:
                  break;
              }
            }
          } else if (userInput.equals("fight")) {
            hero.fight(animal);
          }
        } else if (hero instanceof AmazonWarrior) {
          GameMessages.showAnimalMessageForAmazon(animal);
          String userInput = in.nextLine();
          switch (userInput) {
            case "fight":
              hero.fight(animal);
              break;
            case "skip":
              moveHero(hero, field, currRow, currCol, user);
              break;
              default:
                break;
          }
        }

    } else if (current instanceof Monster) {
        GameMessages.showBribableEnemyMessage((Monster) current);
        String userInput = in.nextLine();
        switch (userInput) {
          case "fight":

            prepareForFight(hero);

            if (current instanceof KingKong) {
              hero.fight((KingKong)current);
            } else if (current instanceof FlyingTiger) {
              hero.fight((FlyingTiger) current);
            } else {
              hero.fight((Chupacabra) current);
            }
            break;
          case "bribe": {
            if (current instanceof KingKong) {
              KingKong kingKong = (KingKong)current;
              hero.bribe((kingKong));
            } else if (current instanceof FlyingTiger) {
              hero.bribe((FlyingTiger) current);
            } else {
              hero.bribe((Chupacabra) current);
            }
            break;
          }
          default:
            break;
        }

    } else if (current instanceof Boss) {
      GameMessages.showBribableEnemyMessage((Boss) current);
      String userInput = in.nextLine();
      switch (userInput) {
        case "fight":
          prepareForFight(hero);
          if (current instanceof Rainmaker) {
            hero.fight((Rainmaker) current);
          } else if (current instanceof LordOfTheFlies) {
            hero.fight((LordOfTheFlies) current);
          }
          break;
        case "bribe": {
          if (current instanceof Rainmaker) {
            hero.bribe((Rainmaker) current);
          } else if (current instanceof LordOfTheFlies) {
            hero.bribe((LordOfTheFlies) current);
          }
          break;
        }
        default:
          break;
      }
    }

    moveHero(hero, field, currRow, currCol, user);
  }

  private void prepareForFight(Hero hero) {
    GameMessages.showSwitchWeaponMessage(hero);
    String userInput = in.nextLine();
    hero.switchWeapon(userInput);

    if (Hero.MAX_LIFE_POINTS - hero.getLifepoints() > Potion.HEALTH_POINTS_GIVEN
            && hero.hasHealthPotion()) {
      GameMessages.showDrinkPotionMessage(hero);
      String userInput2 = in.nextLine();

      if (userInput2.equals("yes")) {
        hero.drink();
      }
    }

  }

  private int[] nextPosition(Object[][] field, int currRow, int currCol, int diceValue) {

    int[] result = new int[2];
    int maxCol = field[currRow].length;
    if (currRow % 2 == 0) {
      //move right
      if (currRow != field.length - 1 && diceValue + currCol >= maxCol) {
        result[0] = currRow + 1;
        diceValue -= ((maxCol - 1) - currCol);
        result[1] = (maxCol - diceValue);
      } else {
        result[0] = currRow;
        result[1] = currCol + diceValue;
      }
    } else {
      //move left
      if (currRow != field.length - 1 && currCol - diceValue < 0) {
        result[0] = currRow + 1;
        diceValue -= currCol;
        result[1] = diceValue - 1;
      } else {
        result[0] = currRow;
        result[1] = currCol - diceValue;
      }
    }

    return result;
  }
}

package junglesurvival;

import junglesurvival.items.jewels.Jewel;
import junglesurvival.items.jewels.JewelColor;
import junglesurvival.items.mushrooms.Mushroom;
import junglesurvival.items.potions.Potion;
import junglesurvival.items.potions.PotionType;
import junglesurvival.items.weapons.Weapon;
import junglesurvival.items.weapons.WeaponType;
import junglesurvival.participants.*;
import junglesurvival.participants.interfaces.Fieldable;

import java.util.Random;

public class Field {

  private Field(){
  }

  public static  Fieldable[][] generateNewField() {
    Fieldable[][] field = new Fieldable[9][9];
    for (Fieldable[] row : field) {
      for (int cell = 1; cell < row.length; cell++) {
        row[cell] = putFieldableElementInTheCell();
      }
    }
    return field;
  }

  private static Fieldable putFieldableElementInTheCell() {
    Random rand = new Random();
    int value = rand.nextInt(18);

    switch (value){
      case 1:
        return new Mushroom(true);
      case 2:
        return new Mushroom(false);
      case 3:
        return new Weapon(WeaponType.SWORD);
      case 4:
        return new Weapon(WeaponType.BOW);
      case 5:
        return new Weapon(WeaponType.TORCH);
      case 6:
        return new Potion(PotionType.HEALTH);
      case 7:
        return new Potion(PotionType.EXPERIENCE);
      case 8:
        return new Jewel(JewelColor.BLUE);
      case 9:
        return new Jewel(JewelColor.RED);
      case 10:
        return new Animal(AnimalType.MONKEY);
      case 11:
        return new Animal(AnimalType.LION);
      case 12:
        return new Animal(AnimalType.ELEPHANT);
      case 13:
        return new Chupacabra();
      case 14:
        return new KingKong();
      case 15:
        return new FlyingTiger();
      case 16:
        return new Rainmaker();
      case 17:
        return new LordOfTheFlies();
      default:
        break;
    }
    return null;
  }

}

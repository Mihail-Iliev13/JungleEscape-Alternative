package junglesurvival.participants;

import junglesurvival.GameMessages;
import junglesurvival.items.jewels.JewelColor;
import junglesurvival.items.weapons.Weapon;
import junglesurvival.items.weapons.WeaponType;

public class AmazonWarrior extends Hero {

  public AmazonWarrior(){
    super(WeaponType.DAGGER, Gender.FEMALE);
  }

  public AmazonWarrior(String name, WeaponType weapon, Gender gender){
    super(name, weapon, gender);
  }

  private void attackWithMagicEyes(Enemy enemy){
      useJewel(JewelColor.BLUE);
      attack(enemy);
      attack(enemy);
  }

  @Override
  protected void attack(Enemy enemy) {
    if(canUseMagicEyes()){
      GameMessages.showMagicEyesMessage(this, enemy);
      attackWithMagicEyes(enemy);
    } else {
      attack(enemy);
    }
  }

  private boolean canUseMagicEyes(){
    return jewels.size() > 0 && hasJewel(JewelColor.BLUE);
  }
}

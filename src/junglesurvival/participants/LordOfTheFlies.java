package junglesurvival.participants;

import junglesurvival.items.weapons.Weapon;
import junglesurvival.participants.interfaces.Flyable;

public class LordOfTheFlies extends Boss implements Flyable{

  @Override
  public void doSomeMagic(Hero hero) {
        int weaponDamagePoints = hero.listOfWeapons().stream().mapToInt(Weapon::getDamagePoints).sum();
        setDamage(getDamage() + weaponDamagePoints);
  }

  @Override
  protected boolean isMagicAvailable() {
    return getLifepoints() < BOSS_STARTING_LIFE_POINTS / 2;
  }

  @Override
  public void attack(Hero hero) {
    doSomeMagic(hero);
    super.attack(hero);
  }

  @Override
  public void flyAwayFrom(Hero hero) {
    int decreasedDamage = hero.getDamage()/ 2;
    reduceLifepoints(decreasedDamage);
    System.out.println("Haha, your weapon doesn't have the same effect on me when I'm up here in the sky!");
  }

}

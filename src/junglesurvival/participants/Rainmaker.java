package junglesurvival.participants;

import junglesurvival.items.weapons.WeaponType;
import junglesurvival.participants.interfaces.Healable;

public class Rainmaker extends Boss implements Healable{

  public static final int HEAL_POINTS_GAINED = 5;
  private static final int MAX_POSSIBLE_HEALS = 3;
  private int healsCount = 0;

  @Override
  public void doSomeMagic(Hero hero) {
      setDamage(getDamage() + hero.getDamage());
  }

  @Override
  protected boolean isMagicAvailable() {
    return getLifepoints() == BOSS_STARTING_LIFE_POINTS;
  }

  @Override
  void concedeAttack(Hero attacker) {

    if (attacker.getCurrentWeapon().getType().equals(WeaponType.TORCH)) {
      reduceLifepoints(attacker.getDamage()/2);
    } else {
      super.concedeAttack(attacker);
    }

    heal(HEAL_POINTS_GAINED);
  }

  @Override
  public void heal(int increaser) {

    if(getLifepoints() % 2 == 0 && healsCount < MAX_POSSIBLE_HEALS) {

      if (getLifepoints() + increaser >= BOSS_STARTING_LIFE_POINTS){
        setLifepoints(BOSS_STARTING_LIFE_POINTS);
      } else {
        setLifepoints(getLifepoints() + increaser);
      }
    }
    healsCount++;
  }
}

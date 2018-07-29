package junglesurvival.participants;

import junglesurvival.GameMessages;

public class KingKong extends Monster {

  private static final int HERO_LIFE_POINTS_WHERE_FIERCE_ATTACK_IS_NO_LONGER_ACTIVE = 75;

  public KingKong(){
    super();
  }
  public KingKong(String name) {
    super(name);
  }


  @Override
  protected void fierceAttack(Hero hero) {
    int acceleratedDamage = this.getDamage() + 1;
    setDamage(acceleratedDamage);
    GameMessages.showFierceAttackMessage(this);
    hero.concedeAttack(this);
  }

  @Override
  protected boolean isFierceAttackAvailable(Hero hero) {
    return hero.getLifepoints() < HERO_LIFE_POINTS_WHERE_FIERCE_ATTACK_IS_NO_LONGER_ACTIVE;
  }
}


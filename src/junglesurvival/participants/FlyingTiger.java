package junglesurvival.participants;

import junglesurvival.GameMessages;
import junglesurvival.participants.interfaces.Flyable;

public class FlyingTiger extends Monster implements Flyable{

  private static final int HERO_LEVEL_BEYOND_WHICH_FIERCE_ATTACK_IS_ACTIVATED = 3;

  public FlyingTiger(){
    super();
  }

  public FlyingTiger(String name) {
    super(name);
  }


  @Override
  protected void fierceAttack(Hero hero) {
      setDamage(getDamage() + 2);
      GameMessages.showFierceAttackMessage(this);
      hero.concedeAttack(this);
  }

  @Override
  protected boolean isFierceAttackAvailable(Hero hero) {
    return hero.getLevel() < HERO_LEVEL_BEYOND_WHICH_FIERCE_ATTACK_IS_ACTIVATED;
  }

  @Override
  public void flyAwayFrom(Hero hero) {
      int decreasedDamage = hero.getDamage()/ 2;
      reduceLifepoints(decreasedDamage);
  }
}

package junglesurvival.participants;

import junglesurvival.GameMessages;

public class Chupacabra extends Monster {

  private static final int HERO_LIFE_POINTS_BEYOND_WHICH_FIERCE_ATTACK_IS_ACTIVATED = 25;

  public Chupacabra(){
    super();
  }

  public Chupacabra(String name) {
    super(name);
  }


  @Override
  protected void fierceAttack(Hero hero) {
    setDamage(getDamage() * 2);
    GameMessages.showFierceAttackMessage(this);
    hero.concedeAttack(this);
  }

  @Override
  protected boolean isFierceAttackAvailable(Hero hero) {
    return hero.getLifepoints() < HERO_LIFE_POINTS_BEYOND_WHICH_FIERCE_ATTACK_IS_ACTIVATED;
  }

}

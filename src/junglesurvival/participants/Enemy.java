package junglesurvival.participants;


import junglesurvival.GameMessages;
import junglesurvival.items.weapons.WeaponType;
import junglesurvival.participants.interfaces.Fieldable;
import junglesurvival.participants.interfaces.Flyable;

public class Enemy extends Participant implements Fieldable {

  private int experiencePointsGiven;

  protected Enemy(String name) {
    super(name);
  }

  protected Enemy() {
    super();
  }

  public int getExperiencePointsGiven() {
    return experiencePointsGiven;
  }

  protected void setExperiencePointsGiven(int experiencePointsGiven) {
    this.experiencePointsGiven = experiencePointsGiven;
  }

  public void attack(Hero hero){
    GameMessages.showAttackMessage(this);
    hero.concedeAttack(this);
  }

  void concedeAttack(Hero attacker){

    if(!attacker.getCurrentWeapon().getType().equals(WeaponType.BOW)) {
      if (this instanceof Flyable) {
        ((Flyable)this).flyAwayFrom(attacker);
      } else {
        reduceLifepoints(attacker.getDamage());
      }
    } else {
      if (this instanceof Flyable) {
        reduceLifepoints(attacker.getDamage()*2);
      } else {
        reduceLifepoints(attacker.getDamage());
      }
    }
  }

  @Override
  public boolean isFieldable() {
    return true;
  }
}

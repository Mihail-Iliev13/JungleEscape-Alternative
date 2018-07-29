package junglesurvival.items.potions;

import junglesurvival.participants.interfaces.Fieldable;

public class Potion implements Fieldable {

  public static final int HEALTH_POINTS_GIVEN = 5;
  private static final int EXPERIENCE_POINTS_GIVEN = 2;

  private PotionType type;

  public Potion(PotionType type){
    this.type = type;
  }

  public PotionType getType() {
    return type;
  }

  public int getPointsGiven() {
    if (getType().equals(PotionType.EXPERIENCE)){
      return EXPERIENCE_POINTS_GIVEN;
    }
    return HEALTH_POINTS_GIVEN;
  }

  @Override
  public boolean isFieldable() {
    return true;
  }

  @Override
  public String toString() {
    return String.format("potion of type %s", this.getType().toString().toLowerCase());
  }
}

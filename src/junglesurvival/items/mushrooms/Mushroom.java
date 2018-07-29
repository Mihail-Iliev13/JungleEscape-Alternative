package junglesurvival.items.mushrooms;

import junglesurvival.participants.interfaces.Fieldable;

public class Mushroom implements Fieldable {

  private boolean isGood;
  private static final int MUSHROOM_POINTS = 5;

  public Mushroom(boolean isGood){
    this.isGood = isGood;
  }

  public boolean isGood() {
    return isGood;
  }

  public int getMushroomPoints() {
    return MUSHROOM_POINTS;
  }

  @Override
  public boolean isFieldable() {
    return true;
  }
}

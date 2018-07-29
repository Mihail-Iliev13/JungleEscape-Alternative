package junglesurvival.items.jewels;

import junglesurvival.participants.interfaces.Fieldable;

public class Jewel implements Fieldable {

  private JewelColor color;

  public Jewel (JewelColor color){
    this.color = color;
  }

  public JewelColor getColor() {
    return color;
  }

  @Override
  public boolean isFieldable() {
    return true;
  }

  @Override
  public String toString() {
    return String.format("%s jewel", this.getColor().toString().toLowerCase());
  }
}

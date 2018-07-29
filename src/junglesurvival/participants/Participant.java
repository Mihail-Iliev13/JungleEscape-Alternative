package junglesurvival.participants;

 public class Participant {

  private String name;
  private int lifepoints;
  private int damage;
  private static final int MIN_NAME_LENGTH = 2;
  private static final int MAX_NAME_LENGTH = 25;


  protected Participant(){
     setName(classAsName());
   }

  protected Participant(String name) {
    setName(name);
  }

    public int getLifepoints() {
    return lifepoints;
  }

    public int getDamage() {
     return damage;
   }

    public String getName() {
     return name;
   }

    void reduceLifepoints(int reducer){

     int currentLifepoints = getLifepoints();
     //when a participant dies its life points are always zero, and not a negative number
     if(currentLifepoints - reducer < 1) {
       setLifepoints(0);
       return;
     }
     setLifepoints(currentLifepoints - reducer);
   }

    boolean isDead(){
     return getLifepoints() <= 0;
   }

    void setLifepoints(int lifepoints) {
    this.lifepoints = lifepoints;
  }

    void setDamage(int damage) {
    this.damage = damage;
  }

  private String classAsName(){
      /*
        when default constructor is called this method
        transforms the name of the class into String
        that is used for the name of the object
       */
     StringBuilder name = new StringBuilder();
     char[] chars = getClass().toString().toCharArray();
     int index = chars.length - 1;
     while (chars[index] != '.'){
       name.append(chars[index]);
       if(Character.isUpperCase(chars[index]))
         name.append(' ');
       index--;
     }
     return name.reverse().toString().trim();
   }

  private void setName(String name){

    if(name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
      System.out.println("Invalid Name"); //TODO: it would be better if the method throws exception
    } else {
      this.name = name;
    }
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nRemaining LifePoints: %d\nAttacking power: %d\n"
            , getName(), getLifepoints(), getDamage());
  }

 }
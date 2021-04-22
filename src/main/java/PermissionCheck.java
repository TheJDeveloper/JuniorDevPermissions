public class PermissionCheck {

    private final int level;
    private final String tag;

    /**
     * Constructs a new PermissionCheck object
     *
     * @param level The permission level to check against
     * @param tag Any tags to check against
     */
    public PermissionCheck(int level, String tag){
        this.level = level;
        this.tag = tag;
    }

    /**
     * Checks if a permission level is at a certain level
     *
     * @param toCheck The permission level of the player
     * @return Whether or not the player has permission
     */
    public boolean canUse(Permission toCheck){
        if(toCheck.getLevel() == -1){return true;}
        if(toCheck.getLevel() >= level){
            return true;
        }
        for(char c: toCheck.getTag().toCharArray()){
            if(tag.contains(Character.toString(c))){return true;}
        }
        return false;
    }


}

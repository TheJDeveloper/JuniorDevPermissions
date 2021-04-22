public class PermissionCheck {

    private int level;
    private String tag;

    public PermissionCheck(int level, String tag){
        this.level = level;
        this.tag = tag;
    }

    public boolean canUse(Permission toCheck){

        if(toCheck.getLevel() >= level){
            return true;
        }
        for(char c: toCheck.getTag().toCharArray()){
            if(tag.contains(Character.toString(c))){return true;}
        }
        return false;
    }


}

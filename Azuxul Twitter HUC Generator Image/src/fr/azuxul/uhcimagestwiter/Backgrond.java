package fr.azuxul.uhcimagestwiter;

/**
 * Created by Azuxul on 10/08/2015.
 */
public enum Backgrond {

    EXTREMEHILS("/assets/backgronds/backgrond5.png", "Montage"),
    SWAPLAND("/assets/backgronds/backgrond4.png", "Marai"),
    OCEAN("/assets/backgronds/backgrond3.png", "Ocean"),
    PLAN("/assets/backgronds/backgrond2.png", "Plain"),
    NETHERPORTAL("/assets/backgronds/backgrond1.png", "Nether Portal"),
    NETHER("/assets/backgronds/backgrond0.png", "Nether"),
    CUSTOM(" ", "Custom");

    private String PATH;
    private String NAME;

    Backgrond(String PATH, String NAME)
    {
        this.PATH = PATH;this.NAME = NAME;
    }

    public void setPath(String path){
    	if(!this.NAME.equalsIgnoreCase(Backgrond.CUSTOM.getName()))return;
    	
    	this.PATH = path;
    }
    public String getPath(){
        return PATH;
    }
    public String getName(){
        return NAME;
    }
    public static Backgrond getBackgrond(String name)
    {
    	for(Backgrond b : Backgrond.values())if(b.getName().equalsIgnoreCase(name))return b;
		return null;
    }
}

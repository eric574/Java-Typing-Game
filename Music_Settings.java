import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Display the music settings screen
 * 
 * @author Eric Liu
 * @version March 9, 2018
 */
public class Music_Settings extends World {

    private Button back, song1_button, song2_button, song3_button, song4_button;
    private GreenfootSound song1 = new GreenfootSound("Dark-Techno-City.mp3");
    private GreenfootSound song2 = new GreenfootSound("Factory-On-Mercury.mp3");
    private GreenfootSound song3 = new GreenfootSound("Night-Winds.mp3");
    private GreenfootSound song4 = new GreenfootSound("Trouble-On-Mercury.mp3");
    private Slider s = new Slider();
    private final int rows = 6, rowHeight = getHeight() / (rows * 2 + 2);
    private final int cols = 3, colWidth = getWidth() / cols;
    private GreenfootImage background;
    private boolean song1_pressed, song2_pressed, song3_pressed, song4_pressed;
    private Sound_Info sound_info = null;
    private Ship_Sprite player;
    private Background_Info background_info;
    private String difflevel, game_mode;
    
    /**
     * Constructor for objects of class Music_Settings.
     * 
     */
    public Music_Settings (String diff, String mode, Sound_Info sound, Ship_Sprite user, Background_Info info) {
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        this.difflevel = diff;
        this.game_mode = mode;
        this.sound_info = sound;
        this.player = user;
        this.background_info = info;
        background = new GreenfootImage(background_info.getType());
        background.scale(800, 700);
        int xcoor = 0, ycoor = 0;
        getBackground().drawImage(background, xcoor, ycoor);
        
        back = new Button("back", new GreenfootImage("back_button.png"));
        song1_button = new Button("song1", new GreenfootImage("song1_button.png"));
        song2_button = new Button("song2", new GreenfootImage("song2_button.png"));
        song3_button = new Button("song3", new GreenfootImage("song3_button.png"));
        song4_button = new Button("song4", new GreenfootImage("song4_button.png"));
        addObject(song1_button, 200, 330);
        addObject(song2_button, 200, 410);
        addObject(song3_button, 200, 490);
        addObject(song4_button, 200, 570);
        addObject(back, 413, 630);
        
        // Add label
        addObject(new Text("Volume:", 17, "white"), 562, 530);
        s.showValue(true);
        addObject(s, 700, (getHeight() + rowHeight) / 2 + 150);
        s.setValue(80);
        
        // Add the instructions
        // User is supposed to start on Song 1 and end on Song 3
        addObject(new Text("Pick a song starting from Song 1", 20, "white"), 450, 330);
        addObject(new Text("and ending on Song 4.", 20, "white"), 450, 370);
        addObject(new Text("Music Settings", 25, "white"), 400, 100);
        
        addObject(new Text("The four songs are listed below. Simply press the button to choose your song!", 22, "white"), 400, 200);
        addObject(new Text("Slider", 20, "white"), 680, 500);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        if (song1_button.isPressed()) {
            removeObjects(getObjects(Text.class));
            addObject(new Text("Music Settings", 25, "white"), 400, 100);
            addObject(new Text("The four songs are listed below. Simply press the button to choose your song!", 22, "white"), 400, 200);
            addObject(new Text("Slider", 20, "white"), 680, 500);
            addObject(new Text("Pick a song starting from Song 1", 20, "white"), 450, 330);
            addObject(new Text("and ending on Song 4.", 20, "white"), 450, 370);
            addObject(new Text("Volume:", 17, "white"), 562, 530);
            addObject(new Text("Use this slider to adjust the", 17, "white"), 660, 570);
            addObject(new Text("volume for the song you picked.", 17, "white"), 660, 600);
            addObject(new Text("You have chosen Song 1", 20, "white"), 660, 450);
            song1.setVolume(s.getPercentage());
            song1.play();
            sound_info = new Sound_Info(1, s.getPercentage());
            song1_pressed = false;
        }
        if (song2_button.isPressed()) {
            removeObjects(getObjects(Text.class));
            addObject(new Text("Music Settings", 25, "white"), 400, 100);
            addObject(new Text("The four songs are listed below. Simply press the button to choose your song!", 22, "white"), 400, 200);
            addObject(new Text("Slider", 20, "white"), 680, 500);
            addObject(new Text("Pick a song starting from Song 1", 20, "white"), 450, 330);
            addObject(new Text("and ending on Song 4.", 20, "white"), 450, 370);
            addObject(new Text("Volume:", 17, "white"), 562, 530);
            addObject(new Text("Use this slider to adjust the", 17, "white"), 660, 570);
            addObject(new Text("volume for the song you picked.", 17, "white"), 660, 600);
            addObject(new Text("You have chosen Song 2", 20, "white"), 660, 450);
            song1.stop();
            song2.setVolume(s.getPercentage());
            song2.play();
            sound_info = new Sound_Info(2, s.getPercentage());
            song2_pressed = false;
        }
        if (song3_button.isPressed()) {
            removeObjects(getObjects(Text.class));
            addObject(new Text("Music Settings", 25, "white"), 400, 100);
            addObject(new Text("The four songs are listed below. Simply press the button to choose your song!", 22, "white"), 400, 200);
            addObject(new Text("Slider", 20, "white"), 680, 500);
            addObject(new Text("Pick a song starting from Song 1", 20, "white"), 450, 330);
            addObject(new Text("and ending on Song 4.", 20, "white"), 450, 370);
            addObject(new Text("Volume:", 17, "white"), 562, 530);
            addObject(new Text("Use this slider to adjust the", 17, "white"), 660, 570);
            addObject(new Text("volume for the song you picked.", 17, "white"), 660, 600);
            addObject(new Text("You have chosen Song 3", 20, "white"), 660, 450);
            song2.stop();
            song3.setVolume(s.getPercentage());
            song3.play();
            sound_info = new Sound_Info(3, s.getPercentage());
            song3_pressed = false;
        }
        if (song4_button.isPressed()) {
            removeObjects(getObjects(Text.class));
            addObject(new Text("Music Settings", 25, "white"), 400, 100);
            addObject(new Text("The four songs are listed below. Simply press the button to choose your song!", 22, "white"), 400, 200);
            addObject(new Text("Slider", 20, "white"), 680, 500);
            addObject(new Text("Pick a song starting from Song 1", 20, "white"), 450, 330);
            addObject(new Text("and ending on Song 4.", 20, "white"), 450, 370);
            addObject(new Text("Volume:", 17, "white"), 562, 530);
            addObject(new Text("Use this slider to adjust the", 17, "white"), 660, 570);
            addObject(new Text("volume for the song you picked.", 17, "white"), 660, 600);
            addObject(new Text("You have chosen Song 4", 20, "white"), 660, 450);
            song3.stop();
            song4.setVolume(s.getPercentage());
            song4.play();
            sound_info = new Sound_Info(4, s.getPercentage());
            song4_pressed = false;
        }
        
        if (Greenfoot.mouseClicked(back)) {
            song1.stop();
            song2.stop();
            song3.stop();
            song4.stop();
            Greenfoot.setWorld(new Settings_Screen(difflevel, game_mode, sound_info, player, background_info));
        }
   }
}

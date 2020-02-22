package csc214.assignment06.Model;

import java.util.ArrayList;


//Class returning a list of games
public class GameListManager {

    private static ArrayList<Game> gameList;

    public static ArrayList<Game> getGameList(){
        if (gameList == null){
            initGameList();
        }
        return gameList;
    }

    public static void initGameList(){
        gameList = new ArrayList<>();
        gameList.add(new Game("Minecraft","Mojang Software","Open World","Minecraft is a game about placing blocks and going on adventures.",2011));
        gameList.add(new Game("Grand Theft Auto V","Rockstar North","Action-Adventure","Developed by series creator Rockstar North, Grand Theft Auto V heads to the city of Los Santos and its surrounding hills, countryside and beaches in the largest and most ambitious game Rockstar has yet created.",2013));
        gameList.add(new Game("Tetris","Alexey Pajitnov","Puzzle","An early puzzle video game in which falling tetrominoes must be manipulated to form complete lines, which are then cleared from the grid.",1984));
        gameList.add(new Game("Super Mario Bros","Nintendo","Platformer","Super Mario Bros. is a video game released for the Family Computer and Nintendo Entertainment System in 1985. It shifted the gameplay away from its single-screen arcade predecessor, Mario Bros., and instead featured side-scrolling platformer levels. ",1985));
        gameList.add(new Game("World of Warcraft","Blizzard","MMORPG","World of Warcraft (WoW) is a massively multiplayer online role-playing game (MMORPG) released in 2004 by Blizzard Entertainment. It is the fourth released game set in the fantasy Warcraft universe, which was first introduced by Warcraft: Orcs & Humans in 1994.",2004));
        gameList.add(new Game("Donkey Kong","Nintendo","Platformer","Donkey Kong first made his appearance in the 1981 arcade machine called Donkey Kong, in which he faced Mario, now Nintendo's flagship character. This game was also the first appearance of Mario, pre-dating the well-known Super Mario Bros. by four years.",1981));
        gameList.add(new Game("Pac-Man","Namco","Arcade","Pac-Man is an arcade game developed by Namco and first released in Japan in May 1980. It was created by Japanese video game designer Toru Iwatani. It was licensed for distribution in the United States by Midway and released in October 1980.",1980));
        gameList.add(new Game("The Elder Scrolls V: Skyrim","Bethesda Games","RPG","The Elder Scrolls V: Skyrim is an open world action role-playing video game developed by Bethesda Game Studios and published by Bethesda Softworks. The game is set two hundred years after the events of Oblivion, and takes place in the fictional province of Skyrim.\n",2011));
        gameList.add(new Game("Star Wars Battlefront","EA DICE","FPS","Star Wars™ Battlefront™ is the ultimate Star Wars battle experience, brought to life by the creators of Battlefield. Fight in epic Star Wars battles on iconic planets and rise through the ranks playing as the heroic Rebellion or the evil Galactic Empire.",2015));
        gameList.add(new Game("Wii Sports","Nintendo","Casual","The game is a collection of five sports simulations, designed to demonstrate the motion-sensing capabilities of the Wii Remote to new players. The five sports included are tennis, baseball, bowling, golf, and boxing.",2006));
        gameList.add(new Game("The Legend of Zelda","Nintendo","RPG","The first Legend of Zelda game appeared on the Famicom Disk System in 1986. It was later converted into a cartridge game for the American NES. The Legend of Zelda, the first game of the series, was first released in Japan on February 21, 1986, on the Famicom Disk System.",1986));
        gameList.add(new Game("Overwatch","Blizzard","FPS","In a time of global crisis, an international task force of heroes banded together to restore peace to a war-torn world: OVERWATCH. It ended the crisis and helped to maintain peace in the decades that followed, inspiring an era of exploration, innovation, and discovery.",2016));
        gameList.add(new Game("Portal","Valve Software","Puzzle","Portal is a first-person puzzle-platform video game developed and published by Valve Corporation. The game consists primarily of a series of puzzles that must be solved by teleporting the player's character and simple objects using \"the Aperture Science Handheld Portal Device\", a device that can create inter-spatial portals between two flat planes. The player-character, Chell, is challenged and taunted by an artificial intelligence named GLaDOS (Genetic Lifeform and Disk Operating System) to complete each puzzle in the Aperture Science Enrichment Center using the portal gun with the promise of receiving cake when all the puzzles are completed.",2007));
        gameList.add(new Game("Half Life 2","Valve Software","FPS","Half-Life 2 is a first-person shooter video game developed and published by Valve Corporation. ... Some years after the events of Half-Life, protagonist Gordon Freeman is woken by the enigmatic G-Man to find the world has been taken over by the alien Combine.",2004));
        gameList.add(new Game("Super Mario World","Nintendo","Platformer","Super Mario World is a 1990 platform video game developed and published by Nintendo for the Super Nintendo Entertainment System (Super NES). ... The game also marked the first appearance of Yoshi, Mario's dinosaur sidekick.",1990));
        gameList.add(new Game("Pong","Atari","Action","Atari engineer Allan Alcorn designed and built Pong as a training exercise. Pong was the first game developed by Atari. After producing Computer Space, Bushnell decided to form a company to produce more games by licensing ideas to other companies.",1972));
        gameList.add(new Game("Space Invaders","Balley","Arcade","Space Invaders is an arcade video game created by Tomohiro Nishikado and released in 1978. It was originally manufactured and sold by Taito in Japan, and was later licensed for production in the United States by the Midway division of Bally.",1978));
        gameList.add(new Game("Call of Duty 4: Modern Warfare","Activision","FPS","Call of Duty 4: Modern Warfare is a first-person shooter video game developed by Infinity Ward and published by Activision. The fourth installment in the Call of Duty series, it was released in 2007 for the PlayStation 3, Xbox 360, and Microsoft Windows.",2007));
        gameList.add(new Game("Call of Duty: Black Ops","Activision","FPS","The hallmark intensity of Call of Duty returns with an epic single-player campaign that takes players deep behind enemy lines as an elite Black Ops soldier engaging in covert warfare, classified operations, and explosive conflicts across the globe.",2010));
        gameList.add(new Game("Counter-Strike","Valve Software","FPS","initially developed and released as a Half-Life modification by Minh \"Gooseman\" Le and Jess Cliffe in 1999, before Le and Cliffe were hired and the game's intellectual property acquired. Counter-Strike was first released by Valve on the Microsoft Windows platform in 2000.",2000));
        gameList.add(new Game("Resident Evil","Capcom","Horror","Resident Evil is a survival horror video game-based media franchise created by Shinji Mikami and Tokuro Fujiwara and owned by the video game company Capcom.",1996));
        gameList.add(new Game("Red Dead Redemption","Rockstar","RPG","Red Dead Redemption is an open world western action-adventure video game developed by Rockstar San Diego and published by Rockstar Games. It was released for the PlayStation 3 and Xbox 360 consoles in May 2010. It is the second title in the Red Dead franchise, after 2004's Red Dead Revolver.",2010));
        gameList.add(new Game("BioShock","Irrational Games","FPS","BioShock is a first-person shooter with role-playing game customization and stealth elements, and is similar to System Shock 2. The player takes the role of Jack as he is guided through Rapture towards various objectives. The player collects various weapons and plasmids as they work their way through enemy forces.\n",2007));
        gameList.add(new Game("Fallout 4","Bethesda Games","RPG","Fallout 4 is an action role-playing video game developed by Bethesda Game Studios and published by Bethesda Softworks for Microsoft Windows, PlayStation 4, and Xbox One. ... It is the first game in the series to feature full voice acting for the protagonist.",2015));
    }
}

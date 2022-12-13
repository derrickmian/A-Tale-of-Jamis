package tiles;

import main.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;





public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        //Creates 10 times of tiles: water, grass, tree, etc.
        tile = new Tile[10];

        //Stores the values in map.txt
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        loadMap("/main/res/maps/worldmap.txt");
    
    }
    
    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/water1.png"));
            tile[2].collision = true;


            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/cobblestone.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/tree.png"));
            tile[4].collision = true;


            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/darkgrass.png"));


        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public void loadMap(String filePath){

        try{

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                
                //Reads a line of text
                String line = br.readLine(); 

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    //Changes the string to integers
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            br.close();


        } catch(Exception e){
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            //Extract a tile number which is stored in mapTileNum[0][0]
            //the entirety of the map data has been stored in the mapTileNum[][]
            int tileNum = mapTileNum[worldCol][worldRow];

            //Checks the tile of world's X and Y (What Tile to put here)
            //World X and Y are the positions on the map
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            //Then, the screen needs to determine where the player is in the world
            //Remember that the player has to be center.
            //So where on the screen do we draw our X and Y 
            //Screen X and Y is where on the screen we draw World X and Y
            //If player is at worldX: 500 and worldY: 500,
            //then player is 500 pixels away from the 0,0 tiles.
            //so the 0,0 tiles of the SCREEN should be drawn 500 tiles to the left 
            //and 500 tiles to the top 
            //TODO: Not sure WHY gp.player.screenX and Y are added here
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            //Optimization:
            //Creates a boundary around the player from the center of the screen,
            //MINUS player.screenX and PLUS player.screenX
            //MINUS player.screenY and PLUS player.screenY
            //Aslong as a tile is in this boundary, draw it.
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
               worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                //tile[tileNum[]] works as an index of the Tile[] tile array.
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){

                worldCol = 0;
                worldRow++;

            }
        
        }



    }
}

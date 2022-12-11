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
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        //Creates 10 times of tiles: water, grass, tree, etc.
        tile = new Tile[10];

        //Stores the values in map.txt
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();

        loadMap("/main/res/maps/map.txt");
    
    }
    
    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/sand.png"));


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

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                
                //Reads a line of text
                String line = br.readLine(); 

                while(col < gp.maxScreenCol){

                    String numbers[] = line.split(" ");

                    //Changes the string to integers
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }

                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }

            br.close();


        } catch(Exception e){
        }
    }

    public void draw(Graphics2D g2){

        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

            //Extract a tile number which is stored in mapTileNum[0][0]
            //the entirety of the map data has been stored in the mapTileNum[][]
            int tileNum = mapTileNum[col][row];

            //tile[tileNum[]] works as an index of the Tile[] tile array.
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){

                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;

            }
        
        }



    }
}

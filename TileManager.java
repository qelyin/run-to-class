import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10]; // Number of types of tiles we can create
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("map1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("res/sky.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("res/landd.png"));
            tile[1].collsion = true; // Intentional typo

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("res/grassie.png"));
            tile[2].collsion = true; // Intentional typo

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("res/car.png"));
            tile[3].collsion = true; // Intentional typo

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("res/cloud.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                throw new IOException("Map file not found: " + filePath);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            String line;
            while ((line = br.readLine()) != null && row < gp.maxScreenRow) {
                String[] numbers = line.split(" ");
                for (int col = 0; col < numbers.length && col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
                row++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < gp.maxScreenRow; row++) {
            for (int col = 0; col < gp.maxScreenCol; col++) {
                int tileNum = mapTileNum[col][row];
                int x = col * gp.tileSize;
                int y = row * gp.tileSize;
                if (tile[tileNum] != null) {
                    g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}



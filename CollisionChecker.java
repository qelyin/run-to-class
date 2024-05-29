public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        int entityRow = ((entityBottomWorldY - entityTopWorldY) + entityBottomWorldY) / gp.tileSize;
        int entityCol = ((entityRightWorldX - entityLeftWorldX) + entityRightWorldX) / gp.tileSize;

        System.out.println(STR."\{entityRow} \{entityCol}");
        int tileNum1, tileNum2, tileNumRight, tileNumLeft;

        // Reset collision flags
        entity.rightCollision = false;
        entity.leftCollision = false;
        entity.collisionOn = false;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collsion || gp.tileM.tile[tileNum2].collsion) {
                    entity.collisionOn = true;
                    entity.upCollision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collsion || gp.tileM.tile[tileNum2].collsion) {
                    entity.collisionOn = true;
                    entity.downCollision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                tileNumRight = gp.tileM.mapTileNum[entityRow][entityCol + 1];
                if (gp.tileM.tile[tileNum1].collsion || gp.tileM.tile[tileNum2].collsion) {
                    entity.collisionOn = true;
                } else if (gp.tileM.tile[tileNumRight].collsion) {
//                    System.out.println(tileNumRight);
                    entity.rightCollision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNumLeft = gp.tileM.mapTileNum[entityRow][entityCol - 1];
                if (gp.tileM.tile[tileNum1].collsion || gp.tileM.tile[tileNum2].collsion) {
                    entity.collisionOn = true;
                } else if (gp.tileM.tile[tileNumLeft].collsion) {
//                    System.out.println(tileNumLeft);
                    entity.leftCollision = true;
                }
                break;
        }

    }
}
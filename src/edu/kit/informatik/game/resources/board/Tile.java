package edu.kit.informatik.game.resources.board;

import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.util.math.Vector2D;

/**
 * A Tile is a single piece of the board, here information about the objects on the board is stored.
 * @author uppyo
 * @version 1.0
 */
public class Tile {
    private final Vector2D position;
    private TileTypes tileType;
    private Spaceship spaceshipOnTile;

    /**
     * Initialise a Tile using a position and the type of object on the tile
     * @param position position of the tile as Vector2D
     * @param tileType type of the tile, f.e cover or spaceship
     */
    public Tile(Vector2D position, TileTypes tileType) {
        this.tileType = tileType;
        this.position = position;
        this.spaceshipOnTile = null;
    }

    /**
     * Get the position of the tile
     * @return position as Vector2D object
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Get the type of object on the tile
     * @return type as part of TileTypes
     */
    public TileTypes getTileType() {
        return tileType;
    }

    /**
     * Set the type of object on the tile, used during configuration or when moving spaceships
     * @param newTile new TileType object-type
     */
    public void setTileType(TileTypes newTile) {
        this.tileType = newTile;
    }

    /**
     * Get the spaceship associated with tile (if a spaceship currently is on the tile)
     * @return Spaceship-Object or null if no Spaceship is on the tile
     */
    public Spaceship getSpaceshipOnTile() {
        return spaceshipOnTile;
    }

    /**
     * Associate a spaceship to a tile and set the tile-type accordingly
     * @param spaceship Spaceship-Object to be moved on the tile
     */
    public void setSpaceship(Spaceship spaceship) {
        this.tileType = TileTypes.SPACESHIP;
        this.spaceshipOnTile = spaceship;
        spaceship.setPosition(this.position);
    }

    /**
     * Remove a spaceship from the tile (if one is present) and set the tile-type accordingly
     */
    public void removeSpaceship() {
        this.spaceshipOnTile = null;
        this.tileType = TileTypes.FREE;
    }

    @Override
    public String toString() {
        if (this.tileType != TileTypes.SPACESHIP) {
            return this.tileType.toString();
        }
        return this.spaceshipOnTile.toString();
    }

}

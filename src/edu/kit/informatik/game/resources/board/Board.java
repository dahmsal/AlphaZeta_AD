package edu.kit.informatik.game.resources.board;

import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.math.Vector2D;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description of the board, on which the game is played. The board is a collection of Tile-Type objects.
 * @author uppyo
 * @version 1.0
 */
public class Board {
    private final List<Tile> board;
    private final int sideLength;

    /**
     * Initialise the board using the predefined side-length. Initially all tiles are empty (not null)
     * @param size side-length of the board, has to be an odd number
     */
    public Board(int size) {
        this.sideLength = size;
        this.board = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board.add(new Tile(Vector2D.getVector(x, y), TileTypes.FREE));
            }
        }
    }

    /**
     * Configure the board according to a user-input setting
     * @param configuration list of tile-types to configure the board
     * @param spaceships a list of all spaceships in chronological add-order, used to assign spaceships to tiles
     */
    public void configureBoard(List<TileTypes> configuration, List<Spaceship> spaceships) {
        for (int i = 0; i < board.size(); i++) {
            if (configuration.get(i).equals(TileTypes.SPACESHIP)) {
                this.board.get(i).setSpaceship(spaceships.get(0));
                spaceships.remove(0);
            }
            this.board.get(i).setTileType(configuration.get(i));
        }
    }

    /**
     * Validate the symmetrical properties of the board. All cover has be placed symmetrical to the sw-ne axis.
     * @return true, if the board-configuration is valid
     */
    public boolean validateSymmetrical() {
        List<Tile> coverTiles = getCoverTiles();
        for (Tile coverTile: coverTiles) {
            Tile mirroredTile = this.getTile(Vector2D.mirrorVector(coverTile.getPosition(), this.sideLength));
            if (mirroredTile.getTileType() != coverTile.getTileType()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validate the integrity of the board. No free tile can be surrounded by cover-tiles as direct neighbors
     * @return true, if the board-configuration is valid
     */
    public boolean validateIntegrity() {
        List<Tile> freeTiles = this.board.stream().filter(tile -> tile.getTileType().equals(TileTypes.FREE))
                .collect(Collectors.toList());
        freeTiles.addAll(this.board.stream().filter(tile -> tile.getTileType().equals(TileTypes.SPACESHIP))
                .collect(Collectors.toList()));
        for (Tile freeTile: freeTiles) {
            for (Vector2D vector2D: Vector2D.getDirectNeighbors(freeTile.getPosition())) {
                if (this.getTile(vector2D) != null) {
                    if (this.getTile(vector2D).getTileType().equals(TileTypes.FREE)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Tile> getCoverTiles() {
        List<Tile> coverTiles = this.board.stream().filter(tile -> tile.getTileType().equals(TileTypes.FULL_COVER))
                .collect(Collectors.toList());
        coverTiles.addAll(this.board.stream().filter(tile -> tile.getTileType().equals(TileTypes.HALF_COVER))
                .collect(Collectors.toList()));
        return coverTiles;
    }
    /**
     * Get the Tile-Object on a specified position
     * @param position position as an Vector2D-Object
     * @return the Tile, if no tile could be found null is returned
     */
    public Tile getTile(Vector2D position) {
        return this.board.stream().filter(tile -> tile.getPosition().equals(position)).findFirst().orElse(null);
    }

    /**
     * Get the cover-value of a tile at a given position
     * @param position position of a tile as vector2D
     * @return 0 if the tile is free or does not exist, 1 for half-cover and 2 for full cover
     */
    public int getCoverValue(Vector2D position) {
        Tile tile = getTile(position);
        if (tile == null) {
            return 0;
        }
        switch (tile.getTileType()) {
            case HALF_COVER:
                return 1;
            case FULL_COVER:
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Find the position of a spaceship on the board
     * @param spaceship spaceship on the board
     * @return position of the spaceship as Vector2D
     * @throws ParameterException if the spaceship could not be found
     */
    public Tile findSpaceship(Spaceship spaceship) throws ParameterException {
        Tile foundTile = getTile(spaceship.getPosition());
        if (foundTile != null) {
            return foundTile;
        }
        else throw new ParameterException("ship could not be found on the board");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (Tile tile: this.board) {
            stringBuilder.append(tile.toString());
            index++;
            if (index == this.sideLength) {
                stringBuilder.append(UtilStrings.getLinebreak());
                index = 0;
            }
        }
        return stringBuilder.toString().trim();
    }
}

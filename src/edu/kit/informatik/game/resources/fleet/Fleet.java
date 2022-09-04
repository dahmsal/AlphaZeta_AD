package edu.kit.informatik.game.resources.fleet;

import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.exception.ParameterException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A list of ships that form a fleet. When instantiated all ships and the collector are created and added to the fleet.
 * @author uppyo
 * @version 1.0
 */
public class Fleet {
    private final List<Spaceship> fleet;
    private final int numberContainers;

    /**
     * Initialise a fleet using player-specific data.
     * @param initChar initial char, based on this char the ships in the fleet are named (ascending order)
     * @param idCollector id-char of the collector
     * @param numberContainers number of containers for the collectors
     * @throws ParameterException if the creation of a collector fails due to exceeding the max-module count
     */
    public Fleet(Character initChar, Character idCollector, int numberContainers) throws ParameterException {
        this.fleet = new ArrayList<>();
        this.numberContainers = numberContainers;
        createFleet(initChar, idCollector);
    }

    /**
     * Get a Battleship from the fleet
     * @param id name of the battleship as char
     * @return the battleship object
     * @throws ParameterException if the element could not be found
     */
    public Battleship getBattleship(char id) throws ParameterException {
        Battleship result = getAllBattleships().stream()
                .filter(spaceship -> spaceship.getId() == id).findFirst().orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new ParameterException("Battleship " + id + " could not be found");
        }
    }

    /**
     * Get a spaceship from the fleet
     * @param id identifier as
     * @return corresponding spaceship
     * @throws ParameterException if the spaceship could not be found in the fleet
     */
    public Spaceship getSpaceship(char id) throws ParameterException {
        Spaceship result = this.fleet.stream().filter(spaceship -> spaceship.getId() == id).findFirst().orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new ParameterException("Spaceship " + id + " could not be found in the fleet!");
        }
    }

    /**
     * Get all Battleships included in the fleet
     * @return a casted list of all Battleships
     */
    public List<Battleship> getAllBattleships() {
        return this.fleet.stream().filter(spaceship -> spaceship.getClass().equals(Battleship.class))
                .map(spaceship-> (Battleship) spaceship).collect(Collectors.toList());
    }

    /**
     * Get all ships of the fleet as list
     * @return list of spaceship-objects
     */
    public List<Spaceship> getAllSpaceships() {
        return this.fleet;
    }

    /**
     * Get the Collector-ship from the fleet
     * @return Collector of the fleet
     */
    public Collector getCollector() {
        return this.fleet.stream().filter(spaceship -> spaceship.getClass().equals(Collector.class))
                .map(spaceship -> (Collector) spaceship).findFirst().orElse(null);
    }

    //dynamic fleet creation
    private void createFleet(char initLetter, char collectorID) throws ParameterException {
        for (int i = 0; i < GameParam.getFleetSize(); i++) {
            char id = (char) (initLetter + i);
            if (id == collectorID) {
                this.fleet.add(new Collector(numberContainers, id));
            } else {
                this.fleet.add(new Battleship(id));
            }
        }
    }
}

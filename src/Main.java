import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    private LinkedList<Person> personList;
    private LinkedList<Group> groups;
    private int biberSize;
    private int woSize;
    private int juffiSize;
    private int pfadiSize;
    private int roverSize;

    public static void main(String[] args) {
        new Main();
    }

    /**
     * Initializing all groups, their size, creating people and assigning groups to them.
     */
    public Main(){
        personList = new LinkedList<>();
        groups = new LinkedList<>();
        groups.add(Group.BIBER);
        groups.add(Group.WÖLFLINGE);
        groups.add(Group.JUFFIS);
        groups.add(Group.PFADIS);
        groups.add(Group.ROVER);
        biberSize = 0;
        woSize = 0;
        juffiSize = 0;
        pfadiSize = 0;
        roverSize = 0;
        createPeople();
        calculatePeople(personList);
    }

    /**
     * Creates people and sets their veto groups.
     */
    private void createPeople() {
        Person carl = new Person("Carl",19,new ArrayList<>());
        carl.getVetos().add(Group.WÖLFLINGE);
        personList.add(carl);
        Person jasper = new Person("Jasper",20,new ArrayList<>());
        //jasper.getVetos().add(Group.PFADIS);
        jasper.getVetos().add(Group.JUFFIS);
        personList.add(jasper);
    }

    /**
     * For every person the method picks a random person left in the list and assigns a group to them based on the size of the other groups.
     * @param personList The list containing every person
     */
    private void calculatePeople(LinkedList<Person> personList) {
        while(!personList.isEmpty()){
            int random = (int) (Math.random() * personList.size());
            Person current = personList.get(random);
            Group smallestGroup = getMinGroup();
            if(!current.getVetos().contains(smallestGroup)){
                incrementGroupSize(smallestGroup);
                current.setGroup(smallestGroup);
                personList.remove(current);
            }
            System.out.println(current.getName()+current.getVetos());
            System.out.println(current.getGroup());
        }
    }

    /**
     * Calculates the smallest group. If more than one group have the same size it picks randomly.
     * @return The smallest group.
     */
    private Group getMinGroup() {
        if (biberSize == woSize && woSize == juffiSize && juffiSize == pfadiSize && pfadiSize == roverSize) {
            int random = (int) (Math.random() * 5);
            switch (random) {
                case 0:
                    return Group.BIBER;
                case 1:
                    return Group.WÖLFLINGE;
                case 2:
                    return Group.JUFFIS;
                case 3:
                    return Group.PFADIS;
                default:
                    return Group.ROVER;
            }
        }else {
            int random = (int) (Math.random()*5);
            Group minGroup = groups.get(random);
            int min = getGroupSize(minGroup);
            Collections.shuffle(groups);
            for(Group group:groups){
                if(group==Group.BIBER&&biberSize<min){
                    min = biberSize;
                    minGroup = Group.BIBER;
                }else if(group==Group.WÖLFLINGE &&woSize<min){
                    min = woSize;
                    minGroup = Group.WÖLFLINGE;
                }else if(group==Group.JUFFIS &&juffiSize<min){
                    min = juffiSize;
                    minGroup = Group.JUFFIS;
                }else if(group==Group.PFADIS &&pfadiSize<min){
                    min = pfadiSize;
                    minGroup = Group.PFADIS;
                }else if(group==Group.ROVER &&roverSize<min){
                    min = roverSize;
                    minGroup = Group.ROVER;
                }
            }
            return minGroup;
        }
    }

    /**
     * Increments the size of a Group. Used after a person got assigned to a group.
     * @param group
     */
    private void incrementGroupSize(Group group){
        switch (group){
            case BIBER -> biberSize++;
            case WÖLFLINGE -> woSize++;
            case JUFFIS -> juffiSize++;
            case PFADIS -> pfadiSize++;
            case ROVER -> roverSize++;
        }
    }

    /**
     * Returns the group size for a specific group.
     * @param group The group where the size is wanted.
     * @return The size of the group
     */
    private int getGroupSize(Group group){
        switch (group){
            case BIBER -> {
                return biberSize;
            }
            case WÖLFLINGE -> {
                return woSize;
            }
            case JUFFIS -> {
                return juffiSize;
            }
            case PFADIS -> {
                return pfadiSize;
            }
            default -> {
                return roverSize;
            }
        }
    }
}
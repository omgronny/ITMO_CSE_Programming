package commands;

import collection.Collection;
import collection.Condition;
import collection.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

/**
 * class that realization the remove by id command
 */

public class RemoveByIdCommand extends Command implements Serializable {

//    private final Vector<StudyGroup> vector;
    private final int id;
    private final String login;

    private boolean isId = true;
    private boolean isUser;
//
    public RemoveByIdCommand(int id, String login) {
//        this.vector = vector;
        this.id = id;
        this.login = login;
    }

    @Override
    public String execute() {

        Collection.setVector(remove(Collection.getVector(), this.id));

        if (this.isId) {
            return ("Такого id нет");
        } else if (isUser) {
            return ("Элемент удален. Посмотреть все элементы можно с помощью команды show");
        } else {
            return "Элемент с таким id пренадлежит не вам";
        }

    }

    /**
     * class that realization the remove by id command
     * @param vector main collection
     * @param id id of deleted element
     * @return main collection without element
     */

    public Vector<StudyGroup> remove(Vector<StudyGroup> vector, int id) {

        boolean isId = true;

        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).getId() == id) {
                this.isUser = vector.get(i).getCreator().equals(this.login);

                //if (this.isUser) { vector.remove(i); }
                if (this.isUser) { vector.get(i).setCondition(Condition.DELETE); }

                isId = false;
                break;

            }
        }

        /*

        try {

            File bufferFile = new File("Instr.txt");

            bufferFile.createNewFile();

            SaveCommand saveCommand = new SaveCommand(vector, "Instr.txt");

            saveCommand.execute();

        } catch (FileNotFoundException e) {
            // если файла нет - нужно создать

        } catch (IOException e) {

        }

         */

        this.isId = isId;

        return vector;


    }

}

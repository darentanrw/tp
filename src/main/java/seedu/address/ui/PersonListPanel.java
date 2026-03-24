package seedu.address.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";

    private final Logic logic;
    private final ObservableList<Person> displayedPersons;

    @FXML
    private Label contactHeaderLabel;

    @FXML
    private Label sortHeaderLabel;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code Logic}.
     */
    public PersonListPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.displayedPersons = logic.getFilteredPersonList();
        personListView.setItems(displayedPersons);
        personListView.setCellFactory(listView -> new PersonListViewCell());

        ObservableList<Person> allPersons = logic.getAddressBook().getPersonList();
        ListChangeListener<Person> headerRefreshListener = c -> updateHeaderLabels();
        allPersons.addListener(headerRefreshListener);
        displayedPersons.addListener(headerRefreshListener);
        updateHeaderLabels();
    }

    /**
     * Refreshes the header labels (e.g. after a command changes sort order).
     */
    public void refreshHeaderLabels() {
        updateHeaderLabels();
    }

    private void updateHeaderLabels() {
        int n = logic.getAddressBook().getPersonList().size();
        if (n == 0) {
            contactHeaderLabel.setText("No Contacts Yet");
        } else if (n == 1) {
            contactHeaderLabel.setText("Tuto: 1 Contact");
        } else {
            contactHeaderLabel.setText("Tuto: " + n + " Contacts");
        }
        sortHeaderLabel.setText(logic.getDisplayedListSortDescription());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}

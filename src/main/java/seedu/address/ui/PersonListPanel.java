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

        initializeListView();
        initializeHeaderListeners();
        updateHeaderLabels();
    }

    private void initializeListView() {
        personListView.setItems(displayedPersons);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        updatePlaceholder();
    }

    private void initializeHeaderListeners() {
        ObservableList<Person> allPersons = logic.getAddressBook().getPersonList();
        ListChangeListener<Person> headerRefreshListener = c -> {
            updateHeaderLabels();
            updatePlaceholder();
        };
        allPersons.addListener(headerRefreshListener);
        displayedPersons.addListener(headerRefreshListener);
    }

    private void updatePlaceholder() {
        if (displayedPersons.isEmpty()) {
            Label placeholder = new Label("No tutors found.");
            placeholder.setStyle("-fx-text-fill: grey; -fx-alignment: center; -fx-padding: 20;");
            personListView.setPlaceholder(placeholder);
        } else {
            personListView.setPlaceholder(null);
        }
    }

    /**
     * Refreshes the header labels (e.g. after a command changes sort order).
     */
    public void refreshHeaderLabels() {
        updateHeaderLabels();
    }

    private void updateHeaderLabels() {
        int totalContacts = logic.getAddressBook().getPersonList().size();
        contactHeaderLabel.setText(getContactHeaderTitle(totalContacts));
        sortHeaderLabel.setText(logic.getDisplayedListSortDescription());
    }

    private String getContactHeaderTitle(int numContacts) {
        if (numContacts == 0) {
            return "Tuto has no Contacts Yet";
        } else if (numContacts == 1) {
            return "Tuto has 1 Contact";
        } else {
            return "Tuto has " + numContacts + " Contacts";
        }
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

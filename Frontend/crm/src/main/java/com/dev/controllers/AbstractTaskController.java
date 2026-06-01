package com.dev.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.controlsfx.control.SearchableComboBox;

import com.dev.services.Navigation;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class AbstractTaskController {
    @FXML
    public BorderPane borderPaneRoot;
    
    @FXML
    public Label taskTitleLabel;
    
    @FXML
    public TextField taskTitle;

    @FXML
    public TextArea taskDescription;

    @FXML
    public TextField taskPrice;

    @FXML
    public DatePicker taskDeadline;
    
    @FXML
    public SearchableComboBox<String> statusesList;

    @FXML
    public SearchableComboBox<String> respManagersList;

    @FXML
    public SearchableComboBox<String> clientsList;

    @FXML
    public ListView<String> employeesListView;

    @FXML
    public TextField searchEmployeesTextField;

    @FXML
    public FlowPane selectedEmployeesContainer;

    @FXML
    public VBox successUpdateModal;

    protected TranslateTransition slideIn;
    protected TranslateTransition slideOut;

    public ObservableList<String> statusesObsList = FXCollections.observableArrayList();
    public ObservableList<String> managersObsList = FXCollections.observableArrayList();
    public ObservableList<String> employeesObsList = FXCollections.observableArrayList();
    public ObservableList<String> clientsObsList = FXCollections.observableArrayList();

    public FilteredList<String> employeesFilteredList = new FilteredList<>(employeesObsList, p -> true);

    public Set<String> checkedEmployees = new HashSet<>();

    protected double rootWidth;

    @FXML
    public void initialize() {
        rootWidth = borderPaneRoot.getPrefWidth();
        successUpdateModal.setTranslateX(rootWidth);

        slideIn = new TranslateTransition(Duration.millis(450), successUpdateModal);
        slideOut = new TranslateTransition(Duration.millis(450), successUpdateModal);

        statusesList.setItems(statusesObsList);
        respManagersList.setItems(managersObsList);
        clientsList.setItems(clientsObsList);
        
        employeesListView.setItems(employeesFilteredList);
        employeesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        employeesListView.setCellFactory(param -> new ListCell<String>() {
            {
                setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        String item = getItem();
                        if (item != null && employeesObsList != null) {
                            employeesObsList.remove(item);

                            HBox chip = createEmployeeChip(item);
                            selectedEmployeesContainer.getChildren().add(chip);
                            checkedEmployees.add(item);

                            employeesListView.getSelectionModel().clearSelection();
                        }
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item);
            }
        });

        handleSearchEmployees();
        showEmployeesListView(false);
    }

    /**
     * Обрабатывает нажатие на кнопку возврата на предыдущую страницу
     */
    public void goBackBtnClick() {
        Navigation.getInstance().loadAndShowBack();
    }

    /**
     * Обрабатывает поиск сотрудников
     */
    public void handleSearchEmployees() {
        searchEmployeesTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            String filter = newVal == null ? "" : newVal.toLowerCase().trim();

            if (filter.isEmpty()) {
                employeesFilteredList.setPredicate(item -> true);
                showEmployeesListView(false);
            } 
            else {
                showEmployeesListView(true);
                employeesFilteredList.setPredicate(item -> 
                    item.toLowerCase().contains(filter)
                );
            }
        });
    }

    /**
     * Показать/скрыть список сотрудников
     */
    public void showEmployeesListView(boolean show) {
        if (show) {
            employeesListView.setVisible(true);
            employeesListView.setManaged(true);
        }
        else {
            employeesListView.setVisible(false);
            employeesListView.setManaged(false);
        }
    }

    /**
     * Добавляет элемент сотрудника в список выбранных
     */
    public HBox createEmployeeChip(String employeeName) {
        Label nameLabel = new Label(employeeName);
        nameLabel.setStyle("-fx-text-fill: #3E88D1;");
        nameLabel.setFont(Font.font(16));

        StackPane closeBtn = new StackPane();
        closeBtn.setCursor(Cursor.HAND);
        
        Line line1 = new Line(0, 0, 15, 15);
        Line line2 = new Line(0, 15, 15, 0);
        for (Line line : List.of(line1, line2)) {
            line.setStroke(Color.web("#ff1c20"));
            line.setStrokeWidth(2);
            line.setFill(Color.web("#ff1c20"));
        }
        closeBtn.getChildren().addAll(line1, line2);

        HBox chipRoot = new HBox(10, nameLabel, closeBtn);
        chipRoot.setStyle("-fx-border-color: #3E88D1; -fx-border-width: 2; -fx-border-radius: 30;");
        chipRoot.setPadding(new Insets(5, 20, 5, 20));
        chipRoot.setAlignment(Pos.CENTER);

        closeBtn.setOnMouseClicked(e -> {
            selectedEmployeesContainer.getChildren().remove(chipRoot);
            checkedEmployees.remove(employeeName);

            if (!employeesObsList.contains(employeeName)) {
                employeesObsList.add(employeeName);
            }
        });

        return chipRoot;
    }

}

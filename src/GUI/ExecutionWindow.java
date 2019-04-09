package GUI;

import Controller.InterpreterController;
import Exceptions.StackException;
import Model.ProgramState;

import Model.Utils.Pair;
import Repository.IRepo;
import Repository.Repo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ExecutionWindow implements Initializable {
    private ProgramState selectedStatement;
    private IRepo repo;
    private InterpreterController StmtController;
    private List<ProgramState> localPrgList;

    @FXML
    private ListView<String> Exestack;

    @FXML
    private TableView<TableObject> SymbolTable;

    @FXML
    private TableColumn<TableObject,Integer> ValueSymT;

    @FXML
    private TableColumn<TableObject,String> NameSymT;


    @FXML
    private TableView<TableObject> FileTable;

    @FXML
    private TableColumn<TableObject,String> FileNColumn;

    @FXML
    private TableColumn<TableObject,Integer> IdentifierColumn;

    @FXML
    private TableView<TableObject> HeapTable;

    @FXML
    private TableColumn<TableObject,String> AddressColumn;

    @FXML
    private TableColumn<TableObject,Integer> ValueColumnHeap;

    @FXML
    private ListView<Integer> PrgStateIdentifier;

    @FXML
    private ListView<Integer> OutputList;

    public void initData(ProgramState prgState)
    {
        this.selectedStatement = prgState;
        List<ProgramState> prgList=new ArrayList<>();
        prgList.add(this.selectedStatement);
        this.repo = new Repo(prgList, "log.txt");
        this.StmtController = new InterpreterController(repo);

        this.localPrgList = this.StmtController.removeCompletedPrg(repo.getPrgList());

        ObservableList<String> list=FXCollections.observableArrayList();
        try {
            list.add(prgState.getStack().top().toString());
            Exestack.setItems(list);
        }catch(StackException e)
        {
            System.out.println("stack is empty!");
        }
        ObservableList<Integer> StateIdentifier=FXCollections.observableArrayList(this.selectedStatement.getID());
        PrgStateIdentifier.setItems(StateIdentifier);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateTables()
    {
        ObservableList<Integer> output = FXCollections.observableArrayList();
        output.addAll(this.selectedStatement.getOutput().getList());
        OutputList.setItems(output);

        ObservableList<Integer> identifier = FXCollections.observableArrayList();
        this.localPrgList.forEach(elem -> {
            identifier.add(elem.getID());
        });
        PrgStateIdentifier.setItems(identifier);

        ObservableList<String> stack = FXCollections.observableArrayList();
        try {
            stack.add(this.selectedStatement.getStack().top().toString());
            stack.addAll(Exestack.getItems());
            Exestack.setItems(stack);
        } catch (StackException e) {
            System.out.println("stack is empty!");
        }

        ObservableList<TableObject> symbolTable = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : this.selectedStatement.getSymbolTable().getContent().entrySet()) {
            symbolTable.add(new TableObject(entry.getKey(), entry.getValue()));
            NameSymT.setCellValueFactory(new PropertyValueFactory<TableObject, String>("symbol"));
            ValueSymT.setCellValueFactory(new PropertyValueFactory<TableObject, Integer>("value"));
        }
        SymbolTable.setItems(symbolTable);

        ObservableList<TableObject> heapTable = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Integer> entry : this.selectedStatement.getHeap().getContent().entrySet()) {
            heapTable.add(new TableObject(String.valueOf(entry.getKey()), entry.getValue()));
            AddressColumn.setCellValueFactory(new PropertyValueFactory<TableObject, String>("symbol"));
            ValueColumnHeap.setCellValueFactory(new PropertyValueFactory<TableObject, Integer>("value"));
        }
        HeapTable.setItems(heapTable);


        ObservableList<TableObject> fileTable = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Pair<String, BufferedReader>> entry : this.selectedStatement.getFileTable().getContent().entrySet()) {
            fileTable.add(new TableObject(entry.getValue().getValue1(), entry.getKey()));
            FileNColumn.setCellValueFactory(new PropertyValueFactory<TableObject, String>("symbol"));
            IdentifierColumn.setCellValueFactory(new PropertyValueFactory<TableObject, Integer>("value"));
        }
        FileTable.setItems(fileTable);
    }

    @FXML
    void RunOneStep(ActionEvent event) {
        if(!PrgStateIdentifier.getSelectionModel().isEmpty()) {

            for(int i=0;i<repo.getPrgList().size();i++)
                if(repo.getPrgList().get(i).getID().equals(PrgStateIdentifier.getSelectionModel().getSelectedItem()))
                    this.selectedStatement=repo.getPrgList().get(i);

            if (this.localPrgList.size() > 0) {
                this.StmtController.oneStepForAllPrg(this.localPrgList);
                this.localPrgList = this.StmtController.removeCompletedPrg(repo.getPrgList());
            }
            if (this.localPrgList.size() == 0) {
                this.StmtController.shutDownExecutor();
                repo.getPrgList().forEach(programState -> programState.getFileTable().setContent(this.StmtController.closeFiles(programState.getFileTable().getContent())));
                repo.setPrgList(this.localPrgList);
            }
            updateTables();
        }
        else if(PrgStateIdentifier.getItems().size()==0)
            System.out.println("the execution is finished!");
        else
            System.out.println("select a programState identifier!");
    }

    @FXML
    void selectionChanged(ActionEvent event)
    {
        for(int i=0;i<repo.getPrgList().size();i++)
            if(repo.getPrgList().get(i).getID().equals(PrgStateIdentifier.getSelectionModel().getSelectedItem()))
            {this.selectedStatement=repo.getPrgList().get(i); break;}
        updateTables();
    }
}

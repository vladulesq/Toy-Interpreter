package GUI;


import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.Pair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.net.URL;

import java.util.ResourceBundle;

public class StatementsWindow implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button okButton;

    @FXML
    void openSecondWindow(ActionEvent event) {
        if(!listView.getSelectionModel().isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("secondWindow.fxml"));
                Parent root1 = (Parent) loader.load();

                ExecutionWindow controller=loader.getController();
                ObservableList<ProgramState> programs=hardCodedExamples();
                int id=listView.getSelectionModel().getSelectedIndex();
                controller.initData(programs.get(id));

                Stage stage = new Stage();
                stage.setTitle("Program Execution");
                stage.getIcons().add(new Image("file:69084.png" ));
                stage.setScene(new Scene(root1));
                stage.show();
                //((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println("Cannot open the second window!");
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list=FXCollections.observableArrayList();
        //list.addAll("v=2;\nPrint(v);","a=2+3*5;\nb=a+1;\nPrint(b);","a=2-2;\nIf a Then v=2 Else v=3;\nPrint(v);");
        //list.addAll("OpenRFile(var_f,\\\"test.in\\\");\nReadFile(var_f,var_c);\nPrint(var_c);\nIf(var_c) Then\n     ReadFile(var_f,var_c); Print(var_c);\nElse\n     Print(0));\nCloseRFile(var_f);");
        //list.addAll("v=10; new(v,20); new(a,22); print(v);","v=10; new(v,20); new(a,22); print(100+rH(v)); print(100+rH(a));","v=10; new(v,20); new(a,22); wH(a,30); print(a); print(rH(a));");
        //list.addAll("v=10; new(v,20); new(a,22); wH(a,30); print(a); print(rH(a)); a=0;","v=6;\nwhile (v-4)\n    print(v); v=v-1;\nprint(v);");
        //list.add("OpenRFile(var_f,\\\"test.in\\\");\nReadFile(var_f,var_c);\nPrint(var_c);\nIf(var_c) Then\n     ReadFile(var_f,var_c); Print(var_c);\nElse\n      Print(0));");
        //list.addAll("a=10+(2<6); Print(a);","a=(10+2)<6;"," v=10; new(a,22);\nfork(wH(a,30);  v=32;  print(v);  print(rH(a)));\nprint(v);print(rH(a));");
        //list.add("a=0;\nc = a?100:200;");
        list.add("for");
        list.add("sleep(10);\nprint(0);");
        list.add("v=10;\n(fork(v=v-1;v=v-1;print(v));\nsleep(10);\nprint(v*10)");
        listView.setItems(list);
    }

    public ObservableList<ProgramState> hardCodedExamples()
    {
        ObservableList<ProgramState> list= FXCollections.observableArrayList();

        /*IMyHeap<Integer,Integer> heap1=new Heap<Integer, Integer>();
        IMyList<Integer> list1=new MyList<Integer>();
        IMyDictionary<String,Integer> table1=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack1=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable1=new FileTable<>();
        IStatement expr1 =  new CompStmt(new AssignStmt("v",new ConstExp(2)), new PrintStmt(new VarExp("v")));
        ProgramState program1 = new ProgramState(stack1, table1, list1, expr1, fileTable1,heap1,1);

        IMyHeap<Integer,Integer> heap2=new Heap<Integer, Integer>();
        IMyList<Integer> list2=new MyList<Integer>();
        IMyDictionary<String,Integer> table2=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack2=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable2=new FileTable<>();
        IStatement expr2 = new CompStmt(new AssignStmt("a", new ArithExp(1,new ConstExp(2),new
                ArithExp(3,new ConstExp(3), new ConstExp(5)))),new CompStmt(new AssignStmt("b",new ArithExp(1,new VarExp("a"), new
                ConstExp(1))), new PrintStmt(new VarExp("b"))));
        ProgramState program2 = new ProgramState(stack2, table2, list2, expr2, fileTable2,heap2,1);

        IMyHeap<Integer,Integer> heap3=new Heap<Integer, Integer>();
        IMyList<Integer> list3=new MyList<Integer>();
        IMyDictionary<String,Integer> table3=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack3=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable3=new FileTable<>();
        IStatement expr3= new CompStmt(new AssignStmt("a", new ArithExp(2,new ConstExp(2), new ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))),
                        new PrintStmt(new VarExp("v"))));
        ProgramState program3 = new ProgramState(stack3, table3, list3, expr3, fileTable3,heap3,1);

        IMyHeap<Integer,Integer> heap4=new Heap<Integer, Integer>();
        IMyList<Integer> list4=new MyList<Integer>();
        IMyDictionary<String,Integer> table4=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack4=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable4=new FileTable<>();
        IStatement expr4=new CompStmt(
                new OpenRFile("var_f","test.in"),
                new CompStmt(
                        new ReadFile(new VarExp("var_f"),"var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new CompStmt(
                                        new IfStmt( new VarExp("var_c"), new CompStmt(new ReadFile(new VarExp("var_f"),"var_c"),new PrintStmt(new VarExp("var_c"))), new PrintStmt(new ConstExp(0))),
                                        new CloseRFile(new VarExp("var_f")))
                        )));
        ProgramState program4 = new ProgramState(stack4, table4, list4, expr4, fileTable4,heap4,1);

        IMyHeap<Integer,Integer> heap5=new Heap<Integer, Integer>();
        IMyList<Integer> list5=new MyList<Integer>();
        IMyDictionary<String,Integer> table5=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack5=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable5=new FileTable<>();
        IStatement expr5=new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new HeapAllocation("v",new ConstExp(20)),
                new CompStmt(new HeapAllocation("a", new ConstExp(22)),new PrintStmt(new VarExp("v")))));
        ProgramState program5 = new ProgramState(stack5, table5, list5, expr5, fileTable5,heap5,1);

                IMyHeap<Integer,Integer> heap6=new Heap<Integer, Integer>();
        IMyList<Integer> list6=new MyList<Integer>();
        IMyDictionary<String,Integer> table6=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack6=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable6=new FileTable<>();
        IStatement expr6=new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new HeapAllocation("v",new ConstExp(20)),
                new CompStmt(new HeapAllocation("a", new ConstExp(22)),new CompStmt(new PrintStmt(new ArithExp(1,
                        new ConstExp(100),new HeapReading("v"))),new PrintStmt(new ArithExp(1,new ConstExp(100),new HeapReading("a")))))));
        ProgramState program6 = new ProgramState(stack6, table6, list6, expr6, fileTable6,heap6,1);

        IMyHeap<Integer,Integer> heap7=new Heap<Integer, Integer>();
        IMyList<Integer> list7=new MyList<Integer>();
        IMyDictionary<String,Integer> table7=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack7=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable7=new FileTable<>();
        IStatement expr7= new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new HeapAllocation("v",new ConstExp(20))
                ,new CompStmt(new HeapAllocation("a",new ConstExp(22)),new CompStmt(new HeapWriting("a",new ConstExp(30)),
                new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new HeapReading("a")))))));
        ProgramState program7 = new ProgramState(stack7, table7, list7, expr7, fileTable7,heap7,1);

                IMyHeap<Integer,Integer> heap8=new Heap<Integer, Integer>();
        IMyList<Integer> list8=new MyList<Integer>();
        IMyDictionary<String,Integer> table8=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack8=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable8=new FileTable<>();
        IStatement expr8= new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new HeapAllocation("v",new ConstExp(20))
                ,new CompStmt(new HeapAllocation("a",new ConstExp(22)),new CompStmt(new HeapWriting("a",new ConstExp(30)),
                new CompStmt(new PrintStmt(new VarExp("a")),new CompStmt(new PrintStmt(new HeapReading("a")),new AssignStmt("a",new ConstExp(0))))))));
        ProgramState program8 = new ProgramState(stack8, table8, list8, expr8, fileTable8,heap8,1);

        IMyHeap<Integer,Integer> heap9=new Heap<Integer, Integer>();
        IMyList<Integer> list9=new MyList<Integer>();
        IMyDictionary<String,Integer> table9=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack9=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable9=new FileTable<>();
        IStatement expr9 = new CompStmt(new AssignStmt("v",new ConstExp(6)),new CompStmt(new WhileStmt
                (new ArithExp(2,new VarExp("v"),new ConstExp(4)),
                        new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp
                                (2,new VarExp("v"),new ConstExp(1))))), new PrintStmt(new VarExp("v"))));
        ProgramState program9 = new ProgramState(stack9, table9, list9, expr9, fileTable9,heap9,1);

        IMyHeap<Integer,Integer> heap10=new Heap<Integer, Integer>();
        IMyList<Integer> list10=new MyList<Integer>();
        IMyDictionary<String,Integer> table10=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack10=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable10=new FileTable<>();
        IStatement expr10=new CompStmt(
                new OpenRFile("var_f","test.in"),
                new CompStmt(
                        new ReadFile(new VarExp("var_f"),"var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new IfStmt( new VarExp("var_c"), new CompStmt(new ReadFile(new VarExp("var_f"),"var_c"),new PrintStmt(new VarExp("var_c"))), new PrintStmt(new ConstExp(0))))
                ));
        ProgramState program10 = new ProgramState(stack10, table10, list10, expr10, fileTable10,heap10,1);

        IMyHeap<Integer,Integer> heap11=new Heap<Integer, Integer>();
        IMyList<Integer> list11=new MyList<Integer>();
        IMyDictionary<String,Integer> table11=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack11=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable11=new FileTable<>();
        IStatement expr11=new CompStmt(new AssignStmt("a",new ArithExp(1,new ConstExp(10),new BooleanExpr("<",new ConstExp(2),new ConstExp(6)))),new PrintStmt(new VarExp("a")));
        ProgramState program11 = new ProgramState(stack11, table11, list11, expr11, fileTable11,heap11,1);

        IMyHeap<Integer,Integer> heap12=new Heap<Integer, Integer>();
        IMyList<Integer> list12=new MyList<Integer>();
        IMyDictionary<String,Integer> table12=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack12=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable12=new FileTable<>();
        IStatement expr12=new AssignStmt("a",new BooleanExpr("<",new ArithExp(1,new ConstExp(10),new ConstExp(2)),new ConstExp(6)));
        ProgramState program12 = new ProgramState(stack12, table12, list12, expr12, fileTable12,heap12,1);

        IMyHeap<Integer,Integer> heap13=new Heap<Integer, Integer>();
        IMyList<Integer> list13=new MyList<Integer>();
        IMyDictionary<String,Integer> table13=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack13=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable13=new FileTable<>();
        IStatement expr13=new CompStmt(new AssignStmt("v",new ConstExp(10)),
                new CompStmt(new HeapAllocation("a",new ConstExp(22)),new CompStmt(new ForkStmt
                        (new CompStmt
                                (new HeapWriting("a",new ConstExp(30)),
                                        new CompStmt(new AssignStmt("v",new ConstExp(32)),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new HeapReading("a")))))),
                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReading("a"))))));
        ProgramState program13 = new ProgramState(stack13, table13, list13, expr13, fileTable13,heap13,1);

        IMyHeap<Integer, Integer> heap14 = new Heap<Integer, Integer>();
        IMyList<Integer> list14=new MyList<Integer>();
        IMyDictionary<String,Integer> table14=new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack14=new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable14=new FileTable<>();
        IStatement statement14 = new CompStmt(new AssignStmt("a", new ConstExp(0)),new CompStmt(new condStmt("c", new VarExp("a"), new ConstExp(100), new ConstExp(200)), new PrintStmt(new VarExp("c"))));
        ProgramState Prgr14 = new ProgramState(stack14, table14, list14, statement14, fileTable14, heap14, 1);
*/
        IMyHeap<Integer, Integer> heap15 = new Heap<Integer, Integer>();
        IMyList<Integer> list15 = new MyList<Integer>();
        IMyDictionary<String,Integer> table15 = new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack15 = new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable15 = new FileTable<>();
        IStatement statement15 = new CompStmt(new AssignStmt("v",new ConstExp(20)),new ForStmt("v", new ConstExp(0), new BooleanExpr("<", new VarExp("v"),new ConstExp(3)), new ArithExp(1,new VarExp("v"), new ConstExp(1)),
                new PrintStmt( new VarExp("v"))));
        ProgramState Prgr15 = new ProgramState(stack15, table15, list15, statement15, fileTable15, heap15, 1);

        IMyHeap<Integer, Integer> heap16 = new Heap<Integer, Integer>();
        IMyList<Integer> list16 = new MyList<Integer>();
        IMyDictionary<String,Integer> table16 = new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack16 = new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable16 = new FileTable<>();
        IStatement statement16 = new CompStmt(new SleepStmt(10), new PrintStmt(new ConstExp(0)));
        ProgramState Prgr16 = new ProgramState(stack16, table16, list16, statement16, fileTable16, heap16, 1);

        IMyHeap<Integer, Integer> heap1 = new Heap<Integer, Integer>();
        IMyList<Integer> list1 = new MyList<Integer>();
        IMyDictionary<String,Integer> table1 = new MyDictionary<String,Integer>();
        IMyStack<IStatement> stack1 = new MyStack<IStatement>();
        IMyFileTable<Integer, Pair<String, BufferedReader>> fileTable1 = new FileTable<>();
        IStatement expr1=new CompStmt(new AssignStmt("v", new ConstExp(10)),new CompStmt(new ForkStmt(
                new CompStmt(new AssignStmt("v", new ArithExp(2,new VarExp("v"),new ConstExp(1))),
                        new CompStmt(new AssignStmt("v", new ArithExp(2,new VarExp("v"),new ConstExp(1))),new PrintStmt(new VarExp("v"))))),
                new CompStmt(new SleepStmt(10), new PrintStmt(new ArithExp(3,new VarExp("v"), new ConstExp(10))))));

        ProgramState Prgr1 = new ProgramState(stack1, table1, list1, expr1, fileTable1, heap1, 1);


        //list.addAll(program1,program2,program3,program4,program5,program6,program7);
        //list.addAll(program8,program9,program10,program11,program12);
        list.add(Prgr15);
        list.add(Prgr16);
        list.add(Prgr1);
        return list;
    }
}

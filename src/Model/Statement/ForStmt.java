package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;

public class ForStmt implements IStatement {

    private String id;
    private IExpression exp1;
    private IExpression exp2;
    private IExpression exp3;

    private IStatement stmt1;

    public ForStmt(String id, IExpression exp1, IExpression exp2, IExpression exp3, IStatement stmt1)
    {
        this.id=id;
        this.exp1=exp1;
        this.exp2=exp2;
        this.exp3=exp3;
        this.stmt1=stmt1;
    }

    public String getId() {
        return id;
    }

    public IExpression getExp1() {
        return exp1;
    }

    public IExpression getExp2() {
        return exp2;
    }

    public IExpression getExp3() {
        return exp3;
    }

    public IStatement getStmt1() {
        return stmt1;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        IStatement stmtInt= new CompStmt(new AssignStmt(id, exp1),new WhileStmt( exp2,new CompStmt(stmt1, new AssignStmt(id, exp3))) );
        state.getStack().push(stmtInt);

        return null;
    }
    @Override
    public String toString()
    {
        return "for("+id+"="+exp1.toString()+";"+exp2.toString()+";"+id+"="+exp3.toString()+")"+stmt1.toString();
    }

    @Override
    public IStatement clone() {
        try{
            return (ForStmt)super.clone();
        }catch (CloneNotSupportedException e)
        {
            return new ForStmt(this.getId(),this.getExp1(),this.getExp2(),this.getExp3(),this.getStmt1());
        }
    }
}
package Controller;

import Exceptions.*;
import Model.ADT.*;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Utils.Pair;
import Repository.IRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class InterpreterController
{
    private IRepo repo;
    private ExecutorService executor;
    public InterpreterController(IRepo r)
    {
        this.repo=r;
        executor = Executors.newFixedThreadPool(2);
    }

    public void oneStepForAllPrg(List<ProgramState> prgList)
    {
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        try {
            List<ProgramState> newPrgList = executor.invokeAll(callList)
                    .stream()
                    .map(future -> {
                                try {
                                    return future.get();
                                } catch (Exception e) {
                                    return null;
                                }
                            }
                    )
                    .filter(p -> p != null)
                    .collect(Collectors.toList());

            //add the new threads to the list of the existing ones
            prgList.addAll(newPrgList);
            prgList.forEach(prg ->repo.logPrgStateExec(prg));
            repo.setPrgList(prgList);
        }
        catch (Exception e){
            System.out.println("cannot create thread 2");
        }

    }

    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList)
    {
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

    public void allSteps()
    {
        //executor = Executors.newFixedThreadPool(2);
        List<ProgramState>  prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0)
        {
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //List<ProgramState> tmpList= repo.getPrgList();
        repo.setPrgList(prgList);
    }

    public void shutDownExecutor()
    {
        executor.shutdownNow();
    }

    private String displayCurrentPrgState(ProgramState state)
    {
        return state.toString();
    }

    private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap)
    {
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public Map<Integer, Pair<String, BufferedReader>> closeFiles(Map<Integer,Pair<String,BufferedReader>> fileTable)
    {
        return fileTable.entrySet().stream()
                .filter(e->{
                    try{
                        BufferedReader bf= fileTable.get(e.getKey()).getValue2();
                        bf.close();
                        return false;
                    }
                    catch (IOException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    return true;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private boolean closeAllFiles(ProgramState state)
    {
        boolean ok=false;
        IMyFileTable<Integer, Pair<String, BufferedReader>> filetable=state.getFileTable();
        for(Map.Entry<Integer,Pair<String,BufferedReader>> val: filetable.getContent().entrySet())
        {
            BufferedReader buff=val.getValue().getValue2();
            try
            {
                ok=true;
                buff.close();
                state.getFileTable().remove(val.getKey());
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage()+"the file cannot be closed!");
            }
            catch (DictionaryException e)
            {
                System.out.println(e.getMessage()+"cannot remove the file from the filetable");
            }
        }
        return ok;
    }
}

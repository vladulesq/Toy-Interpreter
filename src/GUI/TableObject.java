package GUI;

import javafx.beans.property.SimpleStringProperty;

public class TableObject {
    private final SimpleStringProperty symbol = new SimpleStringProperty("");
    private Integer value;

    public TableObject(String symbol,Integer value)
    {
        setSymbol(symbol);
        setValue(value);
    }
    public String getSymbol() {
        return symbol.get();
    }

    public void setSymbol(String symbol) {
        this.symbol.set(symbol);
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value=value;
    }

}

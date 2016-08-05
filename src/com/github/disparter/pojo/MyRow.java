package com.github.disparter.pojo;

import java.util.Arrays;
import java.util.List;

public class MyRow {

    private String parent;
    private String value;
    
    public MyRow(){
        super();
    }
    
    public MyRow(String parent, String value){
        super();
        this.parent = parent;
        this.value = value;
    }
    
    public static List<MyRow> getStubCollection(){
        
        return Arrays.asList(
                new MyRow("Mouse", "15"),
                new MyRow("Mouse", "15"),
                new MyRow("Notebook", "10"),
                new MyRow("Notebook", "200"),
                new MyRow("Teclado", "11"),
                new MyRow("Teclado", "12"),
                new MyRow("Teclado", "13")
        );
        
        
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}

package com.github.disparter.poc.dynamicreports.grid;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;

import com.github.disparter.pojo.MyRow;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GridGroup {

    public GridGroup() {
        build();
    }

    private void build() {
        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldStyle).setBorder(stl.border())
                .setBackgroundColor(Color.LIGHT_GRAY);

        // title, field name data type
        TextColumnBuilder<String> itemColumn = col.column("Item", "parent", type.stringType()).setStyle(columnTitleStyle);
        TextColumnBuilder<String> quantityColumn = col.column("Quantity", "value", type.stringType())
                .setStyle(columnTitleStyle);
        ;
        try {
            report().setColumnStyle(boldCenteredStyle).columns(itemColumn, quantityColumn)
                    .columnGrid(grid.horizontalColumnGridList(itemColumn,
                            grid.horizontalColumnGridList(quantityColumn))

                    ).groupBy(itemColumn).setDataSource(createDataSource()).show();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {

        return new JRBeanCollectionDataSource(MyRow.getStubCollection());

    }

    public static void main(String[] args) {
        new GridGroup();
    }
}

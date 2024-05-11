package guiTableModels;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


public class CenterRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	public CenterRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}

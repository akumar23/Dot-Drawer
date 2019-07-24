package stacklab;

import java.util.*;

public class UndoRedoPainter extends Painter {
	private Stack<Circle> History;

	//private ArrayList<Circle> removed = new ArrayList<>(50);

	UndoRedoPainter(){
		setUndoButtonEnabled(false);
		setRedoButtonEnabled(false);
	}

	// Called when the user pushes the Undo button.

	void undo() {
		if(getHistory().isEmpty()){
			setUndoButtonEnabled(false);
		}
		else if (!getHistory().empty()) {
			setUndoButtonEnabled(true);
			History = getHistory();
			setRedoButtonEnabled(true);
			getUndoHistory().push(History.pop());
			if(getHistory().isEmpty()){
				setUndoButtonEnabled(false);
			}
			repaint();
		}
	}

	// Called when the user pushes the Redo button.

	void redo() {
		if(getUndoHistory().isEmpty()){
			setRedoButtonEnabled(false);
		}
		else if(!getUndoHistory().isEmpty()) {
			History.push(getUndoHistory().pop());
			repaint();
			if(getUndoHistory().isEmpty()){
				setRedoButtonEnabled(false);
			}
		}
		if(!History.isEmpty()){
			setUndoButtonEnabled(true);
		}
	}
	
	
	public static void main(String[] args) {
		new UndoRedoPainter().setVisible(true);
	}
}

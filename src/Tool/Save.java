package Tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import component.Components;
import view.MainPanel;

public class Save {
	private static File file;
	private static File directory =  new File("./data");
	
	public static void save() {
		if(MainPanel.getComponentVc().isEmpty()) {
			JOptionPane.showConfirmDialog(MainPanel.getMainPanel(),"저장할 그림이 없습니다.","저장오류",JOptionPane.CLOSED_OPTION);
		}else {
			if(Save.file==null) {
				saveAs();
			}else {
				saveObject(MainPanel.getComponentVc(), Save.file);
			}
		}
	}
	public static void saveAs() {
		JFileChooser fileChooser = new JFileChooser(Save.directory);
		int returnValue = fileChooser.showSaveDialog(MainPanel.getMainPanel());
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			Save.directory = fileChooser.getCurrentDirectory();
			Save.file = fileChooser.getSelectedFile();
			saveObject(MainPanel.getComponentVc(), Save.file);
		}
	}
	
	public static void openFile() {
		JFileChooser fileChooser = new JFileChooser(Save.directory);
		int returnValue = fileChooser.showOpenDialog(MainPanel.getMainPanel());
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			MainPanel.getComponentVc().clear();
			Save.directory = fileChooser.getCurrentDirectory();
			Save.file = fileChooser.getSelectedFile();
			@SuppressWarnings("unchecked")
			Vector<Components> shapes = (Vector<Components>) Save.readObject(Save.file);
			for(Components component:shapes) {
				MainPanel.getComponentVc().add(component);
			}
		}
	}
	public static Object readObject(File file) {
		try {
			Save.file = file;
			FileInputStream fileInputStream;
			fileInputStream = new FileInputStream(Save.file);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Object object = objectInputStream.readObject();
			objectInputStream.close();
			return object;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveObject(Object object,File file) {
		try {
			Save.file = file;
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(Save.file);
			ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
			objectInputStream.writeObject(object);
			objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

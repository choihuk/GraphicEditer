package constant;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import attributeComponent.AButton;
import attributePanel.AAttributeShapePanel;
import attributePanel.AImagePanel;
import attributePanel.AMenuPanel;
import attributePanel.APenPanel;
import attributePanel.ASelectShapePanel;
import attributePanel.AShapePanel;
import menuComponent.MenuButton;
import shapes.BasedShape;
import shapes.EllipseShape;
import shapes.HexagonShape;
import shapes.LineShape;
import shapes.PolygonShape;
import shapes.PentagonShape;
import shapes.RectangleShape;
import shapes.RoundRectangleShape;
import shapes.StarShape;
import shapes.TriangleShape;
import view.AttributePanel;
import view.AttributePanel.AttributeState;


public class Constant {
	
	public enum EMenuButton{
		x(new MenuButton(new ImageIcon("Image/XImage.png"))),
		menu(new MenuButton(new ImageIcon("Image/MenuImage.png"))),
		shape(new MenuButton(new ImageIcon("Image/ShapeImage.png"))),
		pen(new MenuButton(new ImageIcon("Image/PenImage.png"))),
		selectShape(new MenuButton(new ImageIcon("Image/selectImage.png")));
		private MenuButton menuButton;
		private EMenuButton(MenuButton menuButton) {
			this.menuButton=menuButton;
		}
		public MenuButton getButton() {
			return this.menuButton;
		}
	}
	public enum EAShapeButton implements Cloneable{
		line(new LineShape(), new AButton(new ImageIcon("Image/LineImage.png")),"라인"),
		circle(new EllipseShape(),new AButton(new ImageIcon("Image/CircleImage.png")),"원"),
		square(new RectangleShape(),new AButton(new ImageIcon("Image/SquareImage.png")),"사각형"),
		rSquare(new RoundRectangleShape(),new AButton(new ImageIcon("Image/RSquareImage.png")),"둥근사각형"),
		triangle(new TriangleShape(),new AButton(new ImageIcon("Image/TriangleImage.png")),"삼각형"),
		pentagon(new PentagonShape(), new AButton(new ImageIcon("Image/PentagonImage.png")),"오각형"),
		hexagon(new HexagonShape(), new AButton(new ImageIcon("Image/HexagonImage.png")),"육각형"),
		polygon(new PolygonShape(), new AButton(new ImageIcon("Image/PolygonImage.png")),"다각형"),
		star(new StarShape(), new AButton(new ImageIcon("Image/StarImage.png")),"별");
		
		private BasedShape shape;
		private AButton shapeButton;
		private String name;
		private EAShapeButton(BasedShape shape, AButton shapePanel,String name) {
			this.shape=shape;
			this.shapeButton=shapePanel;
			this.name=name;
		}
		public BasedShape getShape() {
			return this.shape.clone();
		}
		public AButton getButton() {
			return this.shapeButton;
		}
		public String getShapeName() {
			return this.name;
		}
	}
	public enum EAPenButton{
		pen(new AButton(new ImageIcon("Image/PenImage2.png"))),
		hPen(new AButton(new ImageIcon("Image/HPenImage.png"))),
		eraser(new AButton(new ImageIcon("Image/EraserImage.png")));
		
		private AButton penButton;
		private EAPenButton(AButton penButton) {
			this.penButton=penButton;
		}
		public AButton getButton() {
			return this.penButton;
		}
	}
	public enum EASelectButton{
		leftR(new AButton(new ImageIcon("Image/PenImage2.png"))),
		R(new AButton(new ImageIcon("Image/HPenImage.png"))),
		rightR(new AButton(new ImageIcon("Image/EraserImage.png")));
		
		private AButton selectButton;
		private EASelectButton(AButton selectButton) {
			this.selectButton=selectButton;
		}
		public AButton getButton() {
			return this.selectButton;
		}
	}
	public enum EAMenuButton{
		newMenu(new AButton(new ImageIcon("Image/newMenuImage.PNG"))),
		openMenu(new AButton(new ImageIcon("Image/openMenuImage.PNG"))),
		insertMenu(new AButton(new ImageIcon("Image/InsertMenuImage.PNG"))),
		saveMenu(new AButton(new ImageIcon("Image/SaveMenuImage.PNG"))),
		saveAsMenu(new AButton(new ImageIcon("Image/SaveAsMenuImage.PNG"))),
		insertImage(new AButton("이미지 저장", 30));
		private AButton menuButton;
		private EAMenuButton(AButton menuButton) {
			this.menuButton = menuButton;
		}
		public AButton getButton() {
			return this.menuButton;
		}
	}
	public enum EAImageButton{
		origin(new AButton("원본 이미지",41)),
		gray(new AButton("흑백 변환",41));
		private AButton imageButton;
		private EAImageButton(AButton imageButton) {
			this.imageButton = imageButton;
		}
		public AButton getButton() {
			return this.imageButton;
		}
	}
	public enum EAPanel{
		eAShape(AttributePanel.AttributeState.shape),
		eAPen(AttributePanel.AttributeState.pen),
		eAAttributeShape(AttributePanel.AttributeState.attributeShape),
		eASelectShape(AttributePanel.AttributeState.selectShape),
		eAMenu(AttributePanel.AttributeState.menu),
		eAImage(AttributePanel.AttributeState.image);
		private AttributePanel.AttributeState state;
		private EAPanel(AttributePanel.AttributeState state) {
			this.state=state;
		}
		public AttributeState getAttributeState() {
			return this.state;
		}
		public void setPanel(AttributeState state, JPanel jPanel) {
			if(state==AttributeState.shape) {
				new AShapePanel(jPanel);
			}else if(state==AttributeState.pen) {
				new APenPanel(jPanel);
			}else if(state==AttributeState.attributeShape) {
				new AAttributeShapePanel(jPanel);
			}else if(state==AttributeState.selectShape) {
				new ASelectShapePanel(jPanel);
			}else if(state==AttributeState.menu) {
				new AMenuPanel(jPanel);
			}else if(state==AttributeState.image) {
				new AImagePanel(jPanel);
			}
		}
	}
	public enum ECursor {
		eDefault(new Cursor(Cursor.DEFAULT_CURSOR)),
		eMove(new Cursor(Cursor.MOVE_CURSOR)),
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),		
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eRotate(rotateCursor()),// 추가
		eColor(colorCursor());
		private Cursor cursor;
		
		private ECursor(Cursor cursor) {
			this.cursor = cursor;
		}		
		public Cursor getCursor() {
			return this.cursor;
		}
		private static Cursor rotateCursor() {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image cursorImage = toolkit.getImage("Image/rotate.png");
			Cursor cursor = toolkit.createCustomCursor(cursorImage, new Point(10,10), "rotateCursor");
			return cursor;
		}
		private static Cursor colorCursor() {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image cursorImage = toolkit.getImage("Image/pipetteImage.png");
			Cursor cursor = toolkit.createCustomCursor(cursorImage, new Point(10,10), "rotateCursor");
			return cursor;
		}
	}
}